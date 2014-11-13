package globant.svp.rest.controller;

import globant.svp.core.domain.Customer;
import globant.svp.core.domain.Entity;
import globant.svp.core.service.GenericService;
import globant.svp.rest.utility.HttpHeadersUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

	@Autowired
	@Qualifier("CustomerService")
	private GenericService genericService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{account}", method = RequestMethod.GET)
	public ResponseEntity<List<? extends Entity>> getByAccount(
			@PathVariable String account) {
		List<? extends Entity> list;
		Map<String, String> param = new HashMap<String, String>();
		param.put("ACCOUNT", account);
		if (!param.isEmpty()) {
			list = genericService.getAllEntityByRequestParam(param);
		} else {
			list = genericService.getALlEntity();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeadersUtil.HTTP_HEADER_RECORD_FOUND,
				new Integer(list.size()).toString());
		return new ResponseEntity(list, headers, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<? extends Entity>> getCustomers(
			@RequestParam Map<String, String> param) {

		List<? extends Entity> list;
		if (!param.isEmpty()) {
			list = genericService.getAllEntityByRequestParam(param);
		} else {
			list = genericService.getALlEntity();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeadersUtil.HTTP_HEADER_RECORD_FOUND,
				new Integer(list.size()).toString());
		return new ResponseEntity(list, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody final Customer entity) {

		genericService.addService(entity);
		return new ResponseEntity<Customer>(entity, HttpStatus.CREATED);
	}

}
