package com.ZenPack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZenPack.model.ReportHeader;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/reportHeader")
public class ZenPackReportController {

	@Autowired
	private ZenPackServiceImpl service;

	@PostMapping("/create")
	public ResponseEntity<ReportHeader> createReportHeader(@RequestBody ReportHeader reportHeader) {
		if (reportHeader == null || service.checkZenPackName(reportHeader.getReportName())) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		ReportHeader reportHeaderResponse = service.createReportHeader(reportHeader);
		return ResponseEntity.status(HttpStatus.CREATED).body(reportHeaderResponse);
	}

	@GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReportHeader> getAllReportHeader() throws JsonProcessingException {
		return service.getAllReportHeader();
	}

	@DeleteMapping("/delete/{reportId}")
	public String deleteByReportHeaderId(@PathVariable Long reportId) {
		return service.deleteReportHeaderById(reportId);
	}

	@GetMapping("/getReportHeaderById/{reportId}")
	public ReportHeader getReportHeaderById(@PathVariable Long reportId) {
		return service.getReportHeaderById(reportId);
	}
	
	@GetMapping("/getReportHeaderByName/{name}")
	public ReportHeader getReportHeaderByName(@PathVariable String name) {
		return service.getReportHeaderByName(name);
	}
}
