package com.example.application.views;

import com.example.application.backend.models.Magaza;
import com.example.application.backend.models.StokKart;
import com.example.application.backend.services.abstracts.StokKartService;
import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("stokKartlar")
public class StokKartView extends VerticalLayout {
    private final StokKartService stokKartService;
    Grid<StokKart> grid = new Grid<>(StokKart.class);

    Dialog dialog = new Dialog();
    Integer itemIdForEdition4 = 0;
    Button stokKartEkleBtn = new Button("Yeni Stok Kart Ekle", VaadinIcon.PLUS_CIRCLE.create());
    Binder<StokKart> stokKartBinder = new Binder<>();

    HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    Button kaydet = new Button("Kaydet",VaadinIcon.PLUS.create());
    Button iptalEt = new Button("Iptal Et",VaadinIcon.CLOSE.create());
    FormLayout formLayout = new FormLayout();



    public StokKartView(StokKartService stokKartService) {
        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");
        this.stokKartService = stokKartService;
        grid.removeColumnByKey("grupKoduu");
        grid.removeColumnByKey("stokKoduu");
        grid.removeColumnByKey("fiyatt");
        grid.setItems(stokKartService.getAll());

        dialog.setHeight("700px");
        dialog.setWidth("500px");

        dialog.setModal(true);
        TextField aciklama = new TextField("Ürün Açıklaması","Ürün açıklamasını giriniz..");
        TextField fiyat = new TextField("Fiyat","Fiyat giriniz..");
        TextField grupKodu = new TextField("Grup Kodu" +  "Grup Kodunu giriniz..");

        stokKartBinder.bind(aciklama,StokKart::getAciklama,StokKart::setAciklama);
        stokKartBinder.bind(fiyat,StokKart::getFiyatt,StokKart::setFiyatt);
        stokKartBinder.bind(grupKodu,StokKart::getGrupKoduu,StokKart::setGrupKoduu);

        stokKartEkleBtn.addClickListener(event -> stokKartEkleBtnHandler());



        Button btn = new Button("Geri Dön", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("adminPage"));



        formLayout.add(aciklama,fiyat,grupKodu);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout1.add(kaydet,iptalEt);
        dialog.add(new H1("Stok Kart"),formLayout,horizontalLayout1);

        iptalEt.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        kaydet.addClickListener(buttonClickEvent -> {
            StokKart stokKart = new StokKart();
            try {
                stokKartBinder.writeBean(stokKart);

            } catch (ValidationException e) {
                e.printStackTrace();
            }
            stokKart.setStokKodu(itemIdForEdition4);

            if (aciklama.isEmpty() || fiyat.isEmpty() || grupKodu.isEmpty()) {
                Notification notification = new Notification("Boş olan bırakılamaz",2000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                stokKartService.add(stokKart);
                refreshData();
                Notification notification = new Notification("Stok Kart Kaydedildi",3000);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
                dialog.close();
            }

        });

        grid.setWidthFull();
        add(grid,btn,stokKartEkleBtn);
        grid.addComponentColumn(item -> createRemoveButton(grid,item)).setHeader("İşlemler");

    }

    private Component createRemoveButton(Grid<StokKart> grid, StokKart stokKart) {
        @SuppressWarnings("unchecked3")
        Button btnDelete = new Button("Sil");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Stok Kart silinsin mi ?", "Bu Stok Kartı sistemden silmek İstediğinize emin misiniz ?", "Sil",
                    confirmEvent -> {
                        stokKartService.deleteStokKart(stokKart);
                        refreshData();
                    },
                    "Vazgeç", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("Error primary");
            dialog.open();
        });
        Button btnUpdate = new Button("Güncelle");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemIdForEdition4= stokKart.getStokKodu();
            stokKartBinder.readBean(new StokKart());
            dialog.open();


        });
        HorizontalLayout horizontalLayout5 = new HorizontalLayout();
        horizontalLayout5.add(btnDelete,btnUpdate);
        return horizontalLayout5;
    }

    private void refreshData() {
        List<StokKart> stokKarts = new ArrayList<>();
        stokKarts.addAll(stokKartService.getAll());
        grid.setItems(stokKarts);
    }

    private void stokKartEkleBtnHandler() {
        stokKartEkleBtn.addClickListener(event -> {

           stokKartBinder.readBean(new StokKart());
            dialog.open();

        });
    }


}
