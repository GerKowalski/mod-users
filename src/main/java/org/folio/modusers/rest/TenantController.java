package org.folio.modusers.rest;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.folio.modusers.entity.TenantAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_/tenant")
public class TenantController
{
	@Produces({"application/json", "text/plain"})
	@Consumes({"application/json"})
	@PostMapping
	public ResponseEntity postTenant(TenantAttributes ta, @RequestHeader Map<String, String> headers)
	{
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@Produces({"text/plain"})
	@Consumes("text/plain")
	@GetMapping
	public ResponseEntity getTenant(@RequestHeader Map<String, String> headers)
	{
		return new ResponseEntity(HttpStatus.OK);
	}

	@Produces({"text/plain"})
	@Consumes("text/plain")
	@DeleteMapping
	public ResponseEntity deleteTenant(@RequestHeader Map<String, String> headers)
	{
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
