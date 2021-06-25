package es.uv.tfm.graphicservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.tfm.graphicservice.model.Graph;
import es.uv.tfm.graphicservice.repository.GraphRepository;

public interface GraphService<T> {

	
	public List<Graph> getGraphs();
	
	Optional<Graph> getGraphById(String id);

	Graph getGraphByType(String type);
	
	public Graph<T> saveGraph(Graph graph);
	
	public Graph updateGraph(String id, Graph graph);
	
	public void deleteGraph(Graph graph);
	
	void deleteGraphById(String id);

	Graph getGraphByOwner(String owner);




	
}
