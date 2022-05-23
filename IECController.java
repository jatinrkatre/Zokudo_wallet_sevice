package com.wallet.zokudo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.zokudo.dto.request.IECRequest;
import com.wallet.zokudo.dto.request.IECTransactionRequest;
import com.wallet.zokudo.dto.response.IECDataCollection;
import com.wallet.zokudo.dto.response.IECResponse;
import com.wallet.zokudo.dto.response.IECTransactionResponse;
import com.wallet.zokudo.entities.IECRequestLog;
import com.wallet.zokudo.exceptions.IECException;
import com.wallet.zokudo.repositories.IECRequestLogRepository;
import com.wallet.zokudo.services.PrefundRequestService;
import com.wallet.zokudo.services.iblservice.IBLService;
import com.wallet.zokudo.services.iblservice.PaymentTransactionRequest;
import com.wallet.zokudo.util.CommonUtil;
import com.wallet.zokudo.util.FetchIecDataComponent;
import com.wallet.zokudo.util.UpdateClientResponseComponent;
import com.wallet.zokudo.validation.IECValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class IECController {

    private ObjectMapper mapper = new ObjectMapper();

    private final IECRequestLogRepository iecRequestLogRepository;
    private final IECValidation iecValidation;
    private final PrefundRequestService prefundRequestService;
    private final IBLService iblService;

    @Autowired
    public IECController(final IECRequestLogRepository iecRequestLogRepository,
                         final IECValidation iecValidation,
                         final PrefundRequestService prefundRequestService,
                         final IBLService iblService) {
        this.iecRequestLogRepository = iecRequestLogRepository;
        this.iecValidation = iecValidation;
        this.prefundRequestService = prefundRequestService;
        this.iblService = iblService;
    }

    @ApiOperation(value = "IBL Status check", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/ibl/statuscheck")
    public ResponseEntity<?> getIblStatus(HttpServletRequest request) {
        log.info("Inside test api");

        String customerRefNum = request.getParameter("customerRefNum");
        log.info("customerRefNum : {}", customerRefNum);
        PaymentTransactionRequest paymentTransactionRequest = new PaymentTransactionRequest();
        paymentTransactionRequest.setCustomerRefNum(customerRefNum);
        return new ResponseEntity<>(iblService.doStatusCheck(paymentTransactionRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Credit Notification IEC Request", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/credit/testCollection")
    public ResponseEntity<?> creditNotification(HttpServletRequest request) throws IOException, SOAPException {
        log.info("Inside test api");

        String tenderId = request.getParameter("tenderId");
        log.info("TenderId : {}", tenderId);

        SOAPConnection connection = CommonUtil.getNewConnection();

        /*FetchIecDataComponent fetchIecDataComponent = new FetchIecDataComponent(
                tenderId,
                "0b864792-9ae6-47ea-9049-af14f8a6d1a4",
                "R2mD7rK7xM3jG1xB7mI3rC7eT0cU4hP5lL0qF8mU0dU3aJ0bG7",
                "http://tempuri.org/IIBLeTender/FetchIecData"
        );*/

        FetchIecDataComponent fetchIecDataComponent = new FetchIecDataComponent(
                tenderId,
                "074b8039-3823-4999-a1dc-90b9b41ee3d6",
                "F5qS1cI7hR8oR1vB7qT1fM8kP6cR2eO5sO7bJ6nT3nG5fF0oS4",
                "http://tempuri.org/IIBLeTender/FetchIecData"
        );

//        log.info("URL :{}", "https://apig.indusind.com/ibl/prod/IBLeTender");

        /*API Call*/
        SOAPMessage resp = connection.call(fetchIecDataComponent.getSoapApi(), "https://ibluatapig.indusind.com/app/uat/IBLeTender");
//        SOAPMessage resp = connection.call(fetchIecDataComponent.getSoapApi(), "https://apig.indusind.com/ibl/prod/IBLeTender");

        /*API Response from IBL*/
        resp.writeTo(System.err);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resp.writeTo(out);
        String strMsg = new String(out.toByteArray());

        return new ResponseEntity<>(strMsg, HttpStatus.OK);
    }

    @ApiOperation(value = "Credit Notification IEC Request", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/credit/pushResponse")
    public ResponseEntity<?> creditNotification2(HttpServletRequest request) throws IOException, SOAPException {
        log.info("Inside test api");

        String tenderId = request.getParameter("tenderId");
        log.info("TenderId : {}", tenderId);

        SOAPConnection connection = CommonUtil.getNewConnection();

        UpdateClientResponseComponent fetchIecDataComponent = new UpdateClientResponseComponent(
                tenderId,
                "074b8039-3823-4999-a1dc-90b9b41ee3d6",
                "F5qS1cI7hR8oR1vB7qT1fM8kP6cR2eO5sO7bJ6nT3nG5fF0oS4",
                "http://tempuri.org/IIBLeTender/UpdateClientResponse"
        );

        List<IECTransactionResponse> transactionResponses = new ArrayList<>();

        /*API Call*/
        SOAPMessage resp = connection.call(fetchIecDataComponent.getSoapApi(transactionResponses), "https://ibluatapig.indusind.com/app/uat/IBLeTender");

        /*API Response from IBL*/
        resp.writeTo(System.err);
        return new ResponseEntity<>("TESTED", HttpStatus.OK);
    }

    /*@ApiOperation(value = "Credit Notification IEC Request", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/credit/notification", consumes = {MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> creditNotification(@RequestBody final IECRequest iecRequest) {
        log.info("Request Info :{}", iecRequest);

        IECResponse iecResponse = new IECResponse();
        List<IECTransactionResponse> transactionResponses = iecResponse.getTransactionResponses();
        List<IECTransactionRequest> iecTransactionRequests = iecRequest.getTransactionRequests();

        log.info("Size :{}", iecTransactionRequests.size());

        iecTransactionRequests.forEach(iecTransactionRequest -> {
            IECTransactionResponse iecTransactionResponse = new IECTransactionResponse();
            String requestLog = null;
            String responseId = null;
            try {
                requestLog = mapper.writeValueAsString(iecTransactionRequest);
                log.info("JSON Request :{}", requestLog);

                IECDataCollection iecDataCollection = new IECDataCollection();

                *//*Validate the request*//*
                iecValidation.validRequest(iecTransactionRequest, iecDataCollection);

                *//*Validate client details*//*
                iecValidation.validateClientDetails(iecTransactionRequest, iecDataCollection);

                *//*Save prefund request*//*
                prefundRequestService.saveIECPrefundRequest(iecTransactionRequest, iecDataCollection, iecTransactionResponse);
                responseId = iecTransactionResponse.getResponseId();

            } catch (IECException ie) {
                log.info(ie.getMessage(), ie);
                log.error("Exception occurred while giving credit");
                responseId = ie.getResponseId();
                preInvalidResponse(iecTransactionResponse, ie);
            } catch (Exception e) {
                log.info(e.getMessage(), e);
                log.error("Exception occurred while giving credit");
                preExceptionResponse(iecTransactionResponse, iecTransactionRequest.getRequestId(), e.getMessage());
            } finally {
                log.info("Save the logs for IEC");
                saveIECRequestLog(requestLog, iecTransactionRequest.getRequestId(), iecTransactionResponse.getResponseCode(), iecTransactionResponse.getResponseDesc(), responseId);
            }
            transactionResponses.add(iecTransactionResponse);
        });
        iecResponse.setTransactionResponses(transactionResponses);
        return new ResponseEntity<>(iecResponse, HttpStatus.OK);
    }

    private void saveIECRequestLog(final String requestLog, final String requestId, final String requestCode, final String requestDesc, final String responseId) {
        log.info("Saving IEC Request logs for requestLog :{}", requestLog);
        log.info("requestId :{}, requestCode :{}, requestDesc :{}, responseId :{}", requestId, requestCode, requestDesc, responseId);
        IECRequestLog iecRequestLog = new IECRequestLog();
        iecRequestLog.setRequestCode(requestCode);
        iecRequestLog.setRequestDesc(requestDesc);
        iecRequestLog.setRequestId(requestId);
        iecRequestLog.setRequestLog(requestLog);
        iecRequestLog.setResponseId(responseId);
        iecRequestLogRepository.save(iecRequestLog);
        log.info("Request logs saved");
    }

    private void preInvalidResponse(final IECTransactionResponse iecTransactionResponse, IECException iecException) {
        log.info("Preparing the IEC response");
        iecTransactionResponse.setRequestId(iecException.getRequestId());
        iecTransactionResponse.setResponseCode(iecException.getResponseCode());
        iecTransactionResponse.setResponseDesc(iecException.getResponseDesc());
        iecTransactionResponse.setResponseId(iecException.getResponseId());
    }

    private void preExceptionResponse(final IECTransactionResponse iecTransactionResponse,final String requestId, final String errorMsg) {
        log.info("Preparing the IEC response");
        iecTransactionResponse.setRequestId(requestId);
        iecTransactionResponse.setResponseCode("001");
        iecTransactionResponse.setResponseDesc("Rejected-" + errorMsg);
        iecTransactionResponse.setResponseId("R"+requestId);
    }*/
}
