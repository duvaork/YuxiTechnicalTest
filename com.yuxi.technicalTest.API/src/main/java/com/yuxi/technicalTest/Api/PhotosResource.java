package com.yuxi.technicalTest.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuxi.technicalTest.model.PhotosCollection;
import com.yuxi.technicalTest.services.IPhotosManager;

@Path("/photos")
public class PhotosResource {

	@Autowired
	private IPhotosManager photosManager;
	
	@GET
    @Produces("application/json")
    public PhotosCollection getPhotos() throws Exception {

        return photosManager.arrange();
    }
	
}
