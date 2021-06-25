package es.uv.tfm.graphicservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.uv.tfm.graphicservice.model.Graph;
import es.uv.tfm.graphicservice.service.GraphService;

@RestController
@RequestMapping("/graph")
public class GraphController {
	
	
	@Autowired
	private GraphService graphService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getGraphs() {
		try {
			return new ResponseEntity<>(graphService.getGraphs(), HttpStatus.OK); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graphs not found", e);
		}
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createGraph(@RequestBody Graph graph) {
		System.out.println("hello");
		try {
			return new ResponseEntity<>(graphService.saveGraph(graph), HttpStatus.CREATED); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create Graph", e);
		}
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Object> getGraphById(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(graphService.getGraphById(id), HttpStatus.FOUND); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graph not found with id " + id, e);
		}
	}
	
	@GetMapping("/owner/{owner}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Object> getGraphsByOwner(@PathVariable("owner") String owner) {
		try {
			return new ResponseEntity<>(graphService.getGraphByOwner(owner), HttpStatus.FOUND); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graph not found with owner "+ owner, e);
		}
	}
	
	@GetMapping("/type/{type}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Object> getGraphsByType(@PathVariable("type") String type) {
		try {
			return new ResponseEntity<>(graphService.getGraphByType(type), HttpStatus.FOUND); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graph not found with type " + type, e);
		}
	}
	
//	@GetMapping("/{account}/{id}")
//	public ResponseEntity<String> getGraphByAccountAndId(@PathVariable("account") String account, @PathVariable("id") int id) {
//		try {
//			return new ResponseEntity<>("getGraphByAccountAndId", HttpStatus.OK); 
//		} catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found", e);
//		}
//	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> updateGraph(@PathVariable("id") String id, @RequestBody Graph graph) {
		try {
			return new ResponseEntity<>(graphService.updateGraph(id, graph), HttpStatus.OK); 
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graph not found", e);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteGraphById(@PathVariable("id") String id) {
		try {

			graphService.deleteGraphById(id);
			return new ResponseEntity<>("Graph deleted", HttpStatus.OK); 

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Graph not found", e);
		}
	}
	
}
