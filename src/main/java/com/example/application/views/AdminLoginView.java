package com.example.application.views;

import com.example.application.backend.models.Admin;
import com.example.application.backend.models.Magaza;
import com.example.application.backend.services.abstracts.AdminService;
import com.example.application.backend.services.abstracts.MagazaService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("adminLogin")
public class AdminLoginView extends VerticalLayout {
    private final AdminService adminService;
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    public AdminLoginView(AdminService adminService){
        setSizeFull();
        setWidthFull();

        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");


        this.adminService = adminService;
        H1 text = new H1("ADMİN GİRİŞ EKRANI");

        H2 text1 = new H2("LÜTFEN GİRİŞ YAPMAK İÇİN BİLGİLERİNİZİ GİRİNİZ");
        horizontalLayout1.setWidth("100%");
        horizontalLayout1.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setWidth("100%");
        horizontalLayout.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout2.setWidth("100%");
        horizontalLayout2.getStyle().set("border","0px solid #9E9E9E");
        horizontalLayout2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout2.add(text1);
        horizontalLayout1.add(text);


        LoginForm component = new LoginForm();
        component.addLoginListener(loginEvent -> {
            Admin result = adminService.login(loginEvent.getUsername(),loginEvent.getPassword());
            if (result.getId() != 0){
                VaadinSession.getCurrent().getSession().setAttribute("login",result.getId());
                UI.getCurrent().getPage().setLocation("/adminPage");


            }else{
                component.setError(true);
            }
        });
        horizontalLayout.add(component);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout1,horizontalLayout2,horizontalLayout);
    }
}
