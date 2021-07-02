package com.example.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport("./styles.css")
public class MainView extends VerticalLayout {

    public MainView(){
        setSizeFull();
        setWidthFull();

        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");


        VerticalLayout verticalLayout = new VerticalLayout();

        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout layout1 = new HorizontalLayout();
        HorizontalLayout layout2 = new HorizontalLayout();
        layout.setWidth("100%");
        layout.getStyle().set("border","0px solid #9E9E9E");
        layout1.setWidth("100%");
        layout1.getStyle().set("border","0px solid #9E9E9E");
        layout2.setWidth("100%");
        layout2.getStyle().set("border","0px solid #9E9E9E");

        Button btn = new Button("Mağaza Girişi", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("magazaLogin"));
        Button bt1 = new Button("Admin Girişi",VaadinIcon.GLASSES.create(),event-> UI.getCurrent().navigate("adminLogin"));
        H1 t = new H1("PARAKENDE SATIŞ");
        H1 t1 = new H1("SİSTEMİNE HOŞ GELDİNİZ");



        layout.add(bt1,btn);

        layout.setPadding(false);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        layout1.add(t,t1);
        layout1.setPadding(false);
        layout1.setMargin(true);
        layout1.setSpacing(true);
        layout1.setJustifyContentMode(JustifyContentMode.CENTER);


        layout2.setJustifyContentMode(JustifyContentMode.CENTER);

        verticalLayout.add(layout,layout1,layout2);
        add(verticalLayout);
    }
}
