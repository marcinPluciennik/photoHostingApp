package com.springboot.photohostingapp.gui;

import com.springboot.photohostingapp.service.ImageUploader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textField = new TextField();
        com.vaadin.flow.component.button.Button button = new Button("Upload");
        button.addClickListener(clickEvent -> {
            String uploadedImage = imageUploader.uploadFile(textField.getValue());
            Image image = new Image(uploadedImage, "Image doesn't exists :(");
            label.setText("Success, image has been loaded!");
            add(label);
            add(image);
        });

        add(textField);
        add(button);
    }
}
