package com.ridonit.alm.service;

import com.google.common.collect.Lists;
import com.ridonit.alm.payload.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SolmanTransferDtoService {

    public SolmanTransferDto getByAlmId(String almId, String jsonText) {
        SolmanTransferDto request = new SolmanTransferDto();
        request.setAlmId(almId);

        SolmanRequestDto solmanReq = new SolmanRequestDto();

        HeaderDto head = new HeaderDto();
        head.setPriority("1");
        head.setDescription("new Ticket for CloudAlmTicket: " + almId);
        head.setProcessType("ZRIR");
        solmanReq.setHeader(head);

        List<PartnerDto> partners = Lists.newArrayList();
        PartnerDto partner1 = new PartnerDto();
        partner1.setFunction("00000001");
        partner1.setPartnerNo("201");
        partners.add(partner1);

        PartnerDto partner2 = new PartnerDto();
        partner2.setFunction("SMIR0001");
        partner2.setPartnerNo("192");
        partners.add(partner2);
        solmanReq.setPartners(partners);


        List<CustomerDto> cust = Lists.newArrayList();
        CustomerDto cus1 = new CustomerDto();
        cus1.setFieldname("ZZ_SNOW_NUMBER");
        cus1.setValue("inc12345");
        cust.add(cus1);

        CustomerDto cus2 = new CustomerDto();
        cus2.setFieldname("ZZ_ALM_ID");
        cus2.setValue("883323");
        cust.add(cus2);
        solmanReq.setCustomers(cust);


        List<TextDto> texts = Lists.newArrayList();
        TextDto t1 = new TextDto();
        t1.setTextType("ZIR4");
        t1.setContent("request json form cloud: " + jsonText);
        texts.add(t1);

        TextDto t2 = new TextDto();
        t2.setTextType("ZIR4");
        t2.setContent("ein langer und formatierter text");
        texts.add(t2);
        solmanReq.setRichTexts(texts);

        List<TextDto> ntexts = Lists.newArrayList();
        TextDto nt1 = new TextDto();
        nt1.setTextType("ZIR4");
        nt1.setContent("ein langer und formatierter text");
        ntexts.add(nt1);

        TextDto nt2 = new TextDto();
        nt2.setTextType("ZIR4");
        nt2.setContent("ein langer und formatierter text");
        ntexts.add(nt2);
        solmanReq.setTexts(ntexts);

        request.setJson(solmanReq);
        return request;
    }
}
