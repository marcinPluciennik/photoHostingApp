package com.springboot.photohostingapp.gui;

import com.springboot.photohostingapp.service.ImageUpader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUpader imageUpader;

    @Autowired
    public UploadGui(ImageUpader imageUpader) {
        this.imageUpader = imageUpader;

        Label label = new Label();
        TextField textField = new TextField();
        com.vaadin.flow.component.button.Button button = new Button("Upload");
        button.addClickListener(clickEvent -> {
            String uploadedImage = imageUpader.uploadFileAndSaveToDb(textField.getValue());
            com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(uploadedImage, "Image doesn't exists :(");
        });

        label.setText("Success, image has been loaded!");
        add(textField);
        add(button);
    }
}
