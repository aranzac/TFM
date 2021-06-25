package es.uv.tfm.graphicservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.uv.tfm.graphicservice.model.Graph;

@Repository
public interface GraphRepository  extends MongoRepository<Graph, String>{

	
	//Graph findById(String id);
	
	Graph findByType(String type);
	
	Graph findByOwner(String owner);

	void deleteById(Optional<Graph> dGraph);
	
}
