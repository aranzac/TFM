package es.uv.tfm.graphicservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.tfm.graphicservice.model.Graph;
import es.uv.tfm.graphicservice.repository.GraphRepository;

@Service
public class GraphServiceImpl implements GraphService{
	
	@Autowired
	private GraphRepository graphRepository;

	@Override
	public Graph saveGraph(Graph graph) {
		System.out.println(""+graph.getId());
		
		return graphRepository.save(graph);
	}

	@Override
	public Graph updateGraph(String id, Graph graph) {
	
		Optional<Graph> dGraph = getGraphById(id);
		return graphRepository.save(graph);		
	}

	@Override
	public void deleteGraph(Graph graph) {
		graphRepository.delete(graph);		
	}

	@Override
	public void deleteGraphById(String id) {
		
		Optional<Graph> dGraph = getGraphById(id);

		graphRepository.deleteById(dGraph.get().getId());
	}

	@Override
	public List getGraphs() {
		try {
			return graphRepository.findAll();
		}
		catch(Exception e) {
			System.out.println("Graphs not found");

		}
		return null;
	}

	@Override
	public Optional<Graph> getGraphById(String id) {
		try {
			return graphRepository.findById(id);
		}
		catch(Exception e) {
			System.out.println("Graph not found");
			//throw  new ResourceNotFoundException(": No graph found with id " + id);
			//throw new Exception("");
		}
		return null;
	}

	@Override
	public Graph getGraphByType(String type) {
		try {
			return graphRepository.findByType(type);
		}
		catch(Exception e) {
			System.out.println("Graph not found");
		}
		return null;
	}

	@Override
	public Graph getGraphByOwner(String owner) {
		try {
			return graphRepository.findByOwner(owner);
		}
		catch(Exception e) {
			System.out.println("Graph not found");
		}
		return null;		
	}


}
