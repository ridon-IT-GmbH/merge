package com.ridonit.alm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ridonit.alm.model.CloudRequirement;
import com.ridonit.alm.payload.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SolmanTransferDtoService {

    private final ObjectMapper objectMapper;

    public SolmanTransferDto getByAlmId(String almId, CloudRequirement req) throws Exception {
        SolmanTransferDto request = new SolmanTransferDto();
        request.setAlmId(almId);

        SolmanRequestDto solmanReq = new SolmanRequestDto();

        HeaderDto head = new HeaderDto();
        head.setPriority("1");
        head.setDescription(req.getTitle());
        head.setProcessType("ZRIR");
        solmanReq.setHeader(head);

        List<PartnerDto> partners = Lists.newArrayList();
        PartnerDto partner1 = new PartnerDto();
        partner1.setFunction("SMIR0001");
        partner1.setPartnerNo(192);
        partners.add(partner1);

        PartnerDto partner2 = new PartnerDto();
        partner2.setFunction("SMCD0006");
        partner2.setPartnerNo(374);
        partners.add(partner2);
        solmanReq.setPartners(partners);


        List<CustomerDto> cust = Lists.newArrayList();
        CustomerDto cus1 = new CustomerDto();
        cus1.setFieldname("ZZ_SNOW_NUMBER");
        cus1.setValue("inc12345");
        cust.add(cus1);

        CustomerDto cus2 = new CustomerDto();
        cus2.setFieldname("ZZ_ALM_ID");
        cus2.setValue(req.getId());
        cust.add(cus2);
        solmanReq.setCustomers(cust);


        List<TextDto> texts = Lists.newArrayList();
        TextDto t1 = new TextDto();
        t1.setTextType("ZIR4");
        t1.setContent("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        texts.add(t1);
        solmanReq.setRichTexts(texts);


        String jsonString = objectMapper.writeValueAsString(solmanReq);
        request.setJson(jsonString);
        return request;
    }
}
