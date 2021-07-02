package com.example.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("adminPage")
public class AdminPage extends VerticalLayout {

    Button btn = new Button("Mağazalar", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("magazalar"));
    Button btn2 = new Button("Stok Kartları", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("stokKartlar"));
    Button btn3 = new Button("Çıkış Yap", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate(""));
   // HorizontalLayout horizontalLayout = new HorizontalLayout();
    public AdminPage() {

        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");

        setHeightFull();
        setAlignItems(Alignment.CENTER);//puts button in horizontal  center
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(btn,btn2,btn3);

    }
}
