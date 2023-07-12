package com.ridonit.alm.mapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ridonit.alm.mapper.abap.AbapProcess;
import com.ridonit.alm.mapper.calm.CalmField;
import com.ridonit.alm.mapper.calm.CalmProcess;
import com.ridonit.alm.mapper.config.MappingConfig;
import com.ridonit.alm.model.BpMapping;
import com.ridonit.alm.model.MapperConfg;
import com.ridonit.alm.model.StatusConfig;
import com.ridonit.alm.model.TransactionTypeConfig;
import com.ridonit.alm.model.UpdateType;
import com.ridonit.alm.service.BpMappingService;
import com.ridonit.alm.service.MapperConfigService;
import com.ridonit.alm.service.StatusConfigService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
@Slf4j
public class A2CMapper {

	private static final String FIRST = "FIRST";
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String REPLACE = "%REPLACE%";
	private static final String API = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com.eu10.alm.cloud.sap/api/";
	private static final String TASKS = API + "calm-tasks/v1/tasks/";

	private final StatusConfigService statusConfig;
	private final BpMappingService bpMapping;
	private final MapperConfigService mpcConfig;

	/**
	 * als API exposen
	 * 
	 * @param json
	 */
	public void updateCloudALM(String json) {
		Map<String, String> jsonMap = jsonToMap(json);
		String procId = jsonMap.get("process_type");
		AbapProcess proc = new AbapProcess(null, null, null, null, null, jsonMap);
		proc.setProcessType(procId);
		if (isStatusA2C(proc)) {
			CalmProcess calmProc = mapA2C(proc);
			pushToCloud(calmProc);
		}
	}

	private boolean isStatusA2C(AbapProcess proc) {
		List<StatusConfig> statusConfigList = statusConfig.findAll();
		String returnString = getActiveStatus(proc, statusConfigList);
		return !returnString.isBlank();
	}

	private CalmProcess mapA2C(AbapProcess proc) {
		String calmId = getCalmId(proc);
		proc.setCalmId(calmId);
		String title = getTitle(proc);
		String description = getDescription(proc);
		String scopeName = getScopeName(proc);
		String subStatus = getSubStatus(proc);
		String priorityId = getPriorityId(proc);
		String workstream = getWorkstream(proc);
		String assigneeId = getAssigneeId(proc);
		String involvedParties = getInvolvedParties(proc);
		Date startDate = getStartDate(proc);
		Date endDate = getEndDate(proc);
		CalmProcess process = new CalmProcess(null, calmId, title, description, scopeName, subStatus, priorityId,
				workstream, assigneeId, involvedParties, startDate, endDate);
		return process;
	}

	private static Date getEndDate(AbapProcess proc) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Date getStartDate(AbapProcess proc) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getInvolvedParties(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.INVOLVED_PARTIES);
		if (mc != null) {
			String abapBp = getAbapTableValue(proc, "partner[" + REPLACE + "]", "ref_partner_fct", mc.getAbapKey(),
					"ref_partner_no", FIRST);
			returnString = getMappedBp(proc, abapBp);			
		}
		return returnString;
	}

	private String getAssigneeId(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.RESPONSIBLE_ID);
		if (mc != null) {
			String abapBp = getAbapTableValue(proc, "partner[" + REPLACE + "]", "ref_partner_fct", mc.getAbapKey(),
					"ref_partner_no", FIRST);
			returnString = getMappedBp(proc, abapBp);
		}
		return returnString;
	}

	private String getWorkstream(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.WORKSTREAM);
		if (mc != null) {
			String abapBp = getAbapTableValue(proc, "partner[" + REPLACE + "]", "ref_partner_fct", mc.getAbapKey(),
					"ref_partner_no", FIRST);
			returnString = getMappedBp(proc, abapBp);
		}
		return returnString;
	}

	private String getMappedBp(AbapProcess proc, String abapBp) {
		List<BpMapping> bpList = bpMapping.findAll();
		String returnString = "";
		for (BpMapping bp : bpList) {
			if (proc.getProcessType().equals(bp.getTransactionTypeConfig().getAbapTransaction())
					&& abapBp.equals(bp.getAbapBp())) {
				returnString = bp.getCalmBp();
				break;
			}
		}
		return returnString;

	}

	private String getPriorityId(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.PRIORITY);
		if (mc != null) {
			returnString = proc.getJsonMap().getOrDefault(mc.getAbapTable() + "." + mc.getAbapKey(), "");
		}
		return returnString;
	}

	private String getSubStatus(AbapProcess proc) {
		List<StatusConfig> statusConfigList = statusConfig.findAll();
		String returnString = getActiveStatus(proc, statusConfigList);
//		String returnString = getAbapTableValue(proc, "status[" + REPLACE + "]", "status", "E0001", "status", FIRST);
		return returnString;
	}

	private static String getActiveStatus(AbapProcess proc, List<StatusConfig> statusConfigList) {
		String returnString = "";
		for (StatusConfig conf : statusConfigList) {
			TransactionTypeConfig ttc = conf.getTransactionTypeConfig();
			String tA = ttc.getAbapTransaction();
			String processType = proc.getProcessType();
			UpdateType updateType = conf.getUpdateType();
			if (tA.equals(processType) && "A2C".equals(updateType.getTechnicalName())) {
				returnString = getAbapTableValue(proc, "status[" + REPLACE + "]", "status", conf.getAbapStatus(),
						"status", FIRST);
				if (!returnString.isBlank()) {
					returnString = conf.getCalmStatus().getTechnicalName();
					break;
				}
			}
		}
		return returnString;
	}

	private static String getScopeName(AbapProcess proc) {

		return "";
	}

	private String getDescription(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.TEXT);
		if (mc != null) {
			returnString = getAbapTableValue(proc, "rich_texts.data[" + REPLACE + "]", "text_id", mc.getAbapKey(),
					"text_content", LINE_SEPARATOR);
		}
		return returnString;
	}

	private String getTitle(AbapProcess proc) {
		String returnString = "";
		MappingConfig mc = getMappingConfig(proc, CalmField.DESCRIPTION);
		if (mc != null) {
			returnString = proc.getJsonMap().getOrDefault(mc.getAbapTable() + "." + mc.getAbapKey(), "");
		}
		return returnString;
	}

	private String getCalmId(AbapProcess proc) {
		return proc.getJsonMap().getOrDefault("customer_h.zz_alm_id", "");
	}

	private static String getAbapTableValue(AbapProcess proc, String abapPath, String keyPath, String keyValue,
			String contentValue, String concatString) {
		String returnString = "";
		boolean cont = true;
		int i = 0;
		while (cont) {
			String keyString = abapPath.replace(REPLACE, String.valueOf(i));
			String keyValuePath = keyString + "." + keyPath;
			if (proc.getJsonMap().containsKey(keyValuePath)) {
				if (keyValue != null && keyValue.equals(proc.getJsonMap().get(keyValuePath))) {
					if (!returnString.isEmpty()) {
						returnString += concatString;
					}
					String contentValuePath = keyString + "." + contentValue;
					returnString += proc.getJsonMap().get(contentValuePath);
					cont = !FIRST.equals(concatString);
				}
			} else {
				cont = false;
			}
			i++;
		}

		return returnString;
	}

	private MappingConfig getMappingConfig(AbapProcess proc, CalmField calmField) {
		MappingConfig mc = null;
		try {
			List<MapperConfg> fullConf = mpcConfig.findAll();
			List<MapperConfg> filtered = fullConf.stream().filter(
					mpc -> mpc.getTransactionTypeConfig().getAbapTransaction().equalsIgnoreCase(proc.getProcessType()))
					.collect(Collectors.toList());
			MapperConfg conf = filtered.stream()
					.filter(mpc -> calmField.getTechnicalName().equalsIgnoreCase(mpc.getCalmField().getTechnicalName()))
					.reduce((a, b) -> {
						throw new IllegalStateException("Multiple elements: " + a + ", " + b);
					}).get();
			mc = new MappingConfig(null, conf.getAbapType().getTechnicalName(), conf.getAbapKey(),
					conf.getCalmField().getTechnicalName());
		} catch (Exception e) {
			log.error("No Mapping found for calmField {} - proctype {}", calmField.getTechnicalName(),
					proc.getProcessType());
//			mc = new MappingConfig(null, "", "", calmField.getTechnicalName());
		}
		return mc;
	}

	public static void pushToCloud(CalmProcess calmProc) {
		// TODO BERND Auto-generated method stub
		try {
			String url = TASKS + calmProc.getCalmId();

			// TrustManager, der alle Zertifikate akzeptiert
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// SSLContext mit dem TrustManager initialisieren
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			// SSL-Socket-Fabrik erstellen
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient client = new OkHttpClient.Builder()
					.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
					.hostnameVerifier((hostname, session) -> true) // Hostname-�berpr�fung deaktivieren
					.build();

			JsonObject jsonObject = createJsonObject(calmProc);

			Gson gson = new Gson();
			String jsonPayloadString = gson.toJson(jsonObject);

			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, jsonPayloadString);
			Request request = new Request.Builder().url(url).header("Authorization", "Bearer " + getToken())
					.header("DataServiceVersion", "2.0").header("Accept", "application/json").patch(body).build();

			Response response = client.newCall(request).execute();
			String responseBody = response.body().string();

			// Print response
			System.out.println(responseBody);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JsonObject createJsonObject(CalmProcess calmProc) {
		JsonObject jsonObject;
		jsonObject = new JsonObject();
		jsonObject.addProperty("title", calmProc.getTitle());
		jsonObject.addProperty("description", calmProc.getDescription());
		jsonObject.addProperty("scopeId", calmProc.getScopeName());
		jsonObject.addProperty("subStatus", calmProc.getSubStatus());
		jsonObject.addProperty("priorityId", calmProc.getPriorityId());
//		jsonObject.addProperty("startDate", cloudFormat(calmProc.getStartDate()));
//		jsonObject.addProperty("dueDate", cloudFormat(calmProc.getDueDate()));
		jsonObject.addProperty("workstream", calmProc.getWorkstream());
		jsonObject.addProperty("assigneeId", calmProc.getAssigneeId());
		jsonObject.addProperty("involvedParties", calmProc.getInvolvedParties());
		return jsonObject;
	}

	private static String cloudFormat(Date startDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(startDate);
	}

	public static String getToken() throws Exception {

		final String AUTH_URL = "https://ridon-it-gmbh-cloudalm.authentication.eu10.hana.ondemand.com/oauth/token";
		final String CLIENT_ID = "sb-CALMExtensionAPI!b155059|sapcloudalm!b16907";
		final String CLIENT_SECRET = "iNA2jK89j+anYdw0hCCiKKca5bw=";
		// HTTP-Verbindung herstellen
		URL url = new URL(AUTH_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Request-Parameter und Header setzen
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Authorization", "Basic " + getBasicAuthHeader(CLIENT_ID, CLIENT_SECRET));
		connection.setDoOutput(true);

		// Request-Body senden
		String requestBody = "grant_type=client_credentials";
		try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
			byte[] bodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
			outputStream.write(bodyBytes);
			outputStream.flush();
		}

		// Response-Code �berpr�fen und Token extrahieren
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String responseString = response.toString();
				String accessToken = extractAccessToken(responseString);
				return accessToken;
			}
		} else {
			throw new RuntimeException(
					"Fehler beim Abrufen des Access Tokens. Statuscode: " + connection.getResponseCode());
		}
	}

	// Helper method to encode the client ID and client secret in Basic Auth format
	private static String getBasicAuthHeader(String clientId, String clientSecret) {
		String authString = clientId + ":" + clientSecret;
		byte[] authBytes = authString.getBytes(StandardCharsets.UTF_8);
		return Base64.getEncoder().encodeToString(authBytes);
	}

	// Helper method to extract the access token from the OAuth response
	private static String extractAccessToken(String responseString) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject responseJson = (JSONObject) parser.parse(responseString);
		String accessToken = (String) responseJson.get("access_token");
		return accessToken;
	}

	public static Map<String, String> jsonToMap(String json) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			addKeys("", new ObjectMapper().readTree(json), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	private static void addKeys(String currentPath, JsonNode jsonNode, Map<String, String> map) {
		if (jsonNode.isObject()) {
			ObjectNode objectNode = (ObjectNode) jsonNode;
			Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
			String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";
			while (iter.hasNext()) {
				Map.Entry<String, JsonNode> entry = iter.next();
				addKeys(pathPrefix + entry.getKey(), entry.getValue(), map);
			}
		} else if (jsonNode.isArray()) {
			ArrayNode arrayNode = (ArrayNode) jsonNode;
			if (arrayNode.size() > 1) {
				for (int i = 0; i < arrayNode.size(); i++) {
					addKeys(currentPath + "[" + i + "]", arrayNode.get(i), map);
				}
			} else {
				addKeys(currentPath, arrayNode.get(0), map);
			}
		} else if (jsonNode.isValueNode()) {
			ValueNode valueNode = (ValueNode) jsonNode;
			map.put(currentPath, valueNode.asText());
		}
	}
}