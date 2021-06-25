package es.uv.tfm.graphicservice.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection="graph")
public class Graph<T> {

	@Id
	private String id;
	
	private String type;
	 
	private int height;
	
	private int width;
	
	private List<T> data;
	
	private List<String> color;
	
	private String owner;
	
	private String title;
	
	private List<T> attributes;
	
	public Graph() {
        id = new ObjectId().toString();
    }

	public Graph(String id, String type, int height, int width, List<T> data, List<String> color, String owner,
			String title, List<T> attributes) {
		super();
		this.id = id;
		this.type = type;
		this.height = height;
		this.width = width;
		this.data = data;
		this.color = color;
		this.owner = owner;
		this.title = title;
		this.attributes = attributes;
	}
	
	// Spring Data MongoDB adds _class in the mongo documents to handle polymorphic behavior of java inheritance.
	
}
