package com.itc.trn.mis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itc.trn.mis.model.PaymentInfo;
import com.itc.trn.mis.model.PaymentReceipt;
import com.itc.trn.mis.model.TransactionInfo;

@RestController
@RequestMapping("/receipt")
public class PaymentReceiptController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentReceiptController.class);

	@PostMapping(value = "/transreceipt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateTransReceipt(@RequestBody PaymentInfo paymentInfo) {
		try {
			logger.info("PaymentReceiptController-Payload::" + paymentInfo);
			//			
			PaymentReceipt paymentReceipt = new PaymentReceipt(String.valueOf(Math.round(Math.random() * 10000)),
					paymentInfo.getCardNo(), paymentInfo.getAmountSpent(), paymentInfo.getMerchnatId());
			return new ResponseEntity<PaymentReceipt>(paymentReceipt, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while Merchant Payment: ", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
