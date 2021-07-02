package com.example.application.views;

import com.example.application.backend.models.Magaza;
import com.example.application.backend.services.abstracts.MagazaService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("magazalar")
public class MagazalarView extends VerticalLayout {

    private final MagazaService magazaService;
    Dialog dialog = new Dialog();
    Integer itemIdForEdition4 = 0;
    Button magazaEkle = new Button("Yeni Mağaza Ekle", VaadinIcon.PLUS_CIRCLE.create());
    Binder<Magaza> magazaBinder = new Binder<>();
    Grid<Magaza> grid = new Grid<>(Magaza.class);
    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
    Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());
    FormLayout formLayout = new FormLayout();
    public MagazalarView(MagazaService magazaService) {
        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");


        this.magazaService = magazaService;

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField magazaAd = new TextField("Mağaza Adı","Mağazanın adını giriniz..");
        TextField email = new TextField("Mağaza E-mail","Mağazanın E-Mailini giriniz..");
        PasswordField sifre = new PasswordField("Mağaza Şifre","Mağazanın Şifresini giriniz..");

        magazaBinder.bind(magazaAd,Magaza::getMagazaAd,Magaza::setMagazaAd);
        magazaBinder.bind(email,Magaza::getEmail,Magaza::setEmail);
        magazaBinder.bind(sifre,Magaza::getPassword,Magaza::setPassword);

        magazaEkle.addClickListener(event -> magazaEkleBtnHandler());


        formLayout.add(magazaAd,email,sifre);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout1.add(kaydet,iptalEt);
        dialog.add(new H1("Mağaza Ekle"),formLayout,horizontalLayout1);

        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        kaydet.addClickListener(buttonClickEvent -> {
            Magaza magaza = new Magaza();
            try {
                magazaBinder.writeBean(magaza);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            magaza.setId(itemIdForEdition4);

            if (magazaAd.isEmpty() || email.isEmpty() || sifre.isEmpty() ) {
                Notification notification = new Notification("Boş alan bırakılamaz",2000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }  else {
                magazaService.add(magaza);
                Notification notification = new Notification("Mağaza Kaydedildi",3000);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
                dialog.close();
                refreshData();
            }

        });


        grid.removeColumnByKey("satisList");
        grid.setWidthFull();
        grid.setItems(magazaService.getAll());
        Button btn = new Button("Geri Dön", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("adminPage"));
        add(grid,btn,magazaEkle);

        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");
    }


    private void refreshData(){
        List<Magaza> magazas = new ArrayList<>();
        magazas.addAll(magazaService.getAll());
        grid.setItems(magazas);
    }

    private void magazaEkleBtnHandler() {
        magazaEkle.addClickListener(event -> {

            magazaBinder.readBean(new Magaza());
            dialog.open();

        });
    }

    private HorizontalLayout createRemoveButton(Grid<Magaza> grid, Magaza magaza) {
        @SuppressWarnings("unchecked3")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Mağaza silinsin mi ?", "Bu mağazayı sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        magazaService.deleteMagaza(magaza);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition4= magaza.getId();
            magazaBinder.readBean(new Magaza());
            dialog.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }
}
