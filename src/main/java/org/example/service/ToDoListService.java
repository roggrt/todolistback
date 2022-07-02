package org.example.service;

import org.example.model.ToDoList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Path("/to-do-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoListService {

    private Set<ToDoList> toDoLists = new HashSet<>();

    public ToDoListService() {
        toDoLists.add(new ToDoList("Test1", "desc test1", false));
        toDoLists.add(new ToDoList("Test2", "desc test2", false));
        toDoLists.add(new ToDoList("Test3", "desc test3", false));
    }

    @GET
    public Set<ToDoList> list() {
        return toDoLists;
    }

    @GET
    @Path("/search/{title}")
    public Set<ToDoList> filterToDos(@PathParam("title") String title) {
    	Set<ToDoList> filteredToDos = new HashSet<>();
    	
    	if (!title.equals("all")) {
    		for (ToDoList todo: toDoLists) {
    			String toDoTitle = todo.getTitle();
        		if (toDoTitle.toLowerCase().contains(title.toLowerCase())) {
        			filteredToDos.add(todo);
        		}
        	}
    	}
    	
        return title.equals("all") ? toDoLists : filteredToDos;
    }

    @GET
    @Path("/{title}")
    public ToDoList getToDo(@PathParam("title") String title) {
    	ToDoList toDoRes = new ToDoList("", "", false);
    	
        toDoLists.forEach(toDo -> {
        	if (toDo.getTitle().equals(title)) {
        		toDoRes.setTitle(toDo.getTitle());
        		toDoRes.setDescription(toDo.getDescription());
        		toDoRes.setDone(toDo.getDone());
        	}
        });
        
        return toDoRes;
    }

    @POST
    public Set<ToDoList> add(ToDoList element) {
        toDoLists.add(element);
        return toDoLists;
    }

    @DELETE
    @Path("/{title}")
    public Set<ToDoList> delete(@PathParam("title") String title) {
        toDoLists.removeIf(value -> value.getTitle().contentEquals(title));
        return toDoLists;
    }

    @PUT
    @Path("/{title}")
    public Set<ToDoList> update(ToDoList element, @PathParam("title") String title) {
        toDoLists.forEach(toDo -> {
            if (toDo.getTitle().equals(title)) {
            	toDo.setTitle(element.getTitle());
            	toDo.setDone(element.getDone());
                toDo.setDescription(element.getDescription());
            }
        });
        return toDoLists;
    }
}