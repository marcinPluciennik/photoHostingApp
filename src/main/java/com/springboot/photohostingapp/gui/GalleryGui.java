package com.springboot.photohostingapp.gui;

import com.springboot.photohostingapp.model.Image;
import com.springboot.photohostingapp.repo.ImageRepo;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepo imageRepo;

    @Autowired
    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        List<Image> images = imageRepo.findAll();
        images.stream()
                .forEach(element -> {
                    com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAddress(), "brak");
                    add(image);
                } );
    }
}
