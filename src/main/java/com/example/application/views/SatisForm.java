package com.example.application.views;

import com.example.application.backend.models.Magaza;
import com.example.application.backend.models.Satis;
import com.example.application.backend.models.StokKart;
import com.example.application.backend.services.abstracts.SatisService;
import com.example.application.backend.services.abstracts.StokKartService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

public class SatisForm extends FormLayout {
    private Notification satınAlindi= new Notification("Satın alma işlemi başarıyla gerçekleşti",3000);
    private Notification error= new Notification("Lütfen ödeme tipini seçiniz.",3000);
    private StokKartService stokKartService;
    private TextField aciklama = new TextField("Açıklama");
    private TextField fiyat = new TextField("Fiyat");
    private TextField grupKodu = new TextField("Grup Kodu");
    private TextField stokKodu = new TextField("Stok Kodu");
    private Button satinAl = new Button("Satın Al");
    private Button cık = new Button("Temizle");
    private  SatisService satisService;
    private int magazaId = Integer.parseInt(VaadinSession.getCurrent().getSession().getAttribute("magazaId").toString());
   //  Binder<Satis> satisTipiBinder = new BeanValidationBinder<>(Satis.class);
    Binder<StokKart> binder = new BeanValidationBinder<>(StokKart.class);

    Button btnRapor = new Button("İşlem Raporları", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("satisRapor"));

     private ComboBox<Satis.SatisTipi> comboBox = new ComboBox<>("Ödeme Türü");
    // private TextField magaza = new TextField("Mağaza Adı");


    public SatisForm(SatisService satisService){
        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");
       // getElement().setAttribute("theme","dark");
        comboBox.setRequired(true);
        aciklama.setReadOnly(true);
        fiyat.setReadOnly(true);
        grupKodu.setReadOnly(true);
        stokKodu.setReadOnly(true);
        this.satisService = satisService;
        // satisTipiBinder.forField(comboBox).asRequired("Lütfen Ödeme Tipini Seçiniz").bind("satisTipi");
        binder.forField(fiyat).bind(StokKart::getFiyatt,StokKart::setFiyatt);
        binder.forField(aciklama).bind(StokKart::getAciklama,StokKart::setAciklama);
        binder.forField(grupKodu).bind(StokKart::getGrupKoduu,StokKart::setGrupKoduu);
        binder.forField(stokKodu).bind(StokKart::getStokKoduu,StokKart::setGrupKoduu);

       comboBox.setItems(Satis.SatisTipi.values());
       comboBox.setHelperText("Lütfen Ödeme Tipini Seçiniz");


        addClassName("satis-form");
        add(stokKodu,aciklama,fiyat,grupKodu,comboBox,buttonsLayout());

        satinAl.addClickListener(event -> {

            if (comboBox.isEmpty()) {
                Notification notificationEmpty = new Notification("Ödeme Türü Seçmek Zorundasınız",3000);
                notificationEmpty.setPosition(Notification.Position.MIDDLE);
                notificationEmpty.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notificationEmpty.open();
            } else {
                Magaza magaza = new Magaza();
                magaza.setId(magazaId);

                Satis satis = new Satis();
                satis.setMagaza(magaza);
                satis.setSatisTipi(comboBox.getValue());
                satis.setStokKodu(Integer.parseInt(stokKodu.getValue()));

                satisService.add(satis);
                satınAlindi.setPosition(Notification.Position.MIDDLE);
                satınAlindi.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                satınAlindi.open();
                clearForm();

            }
        });
        cık.addClickListener(event -> {
           clearForm();
        });
    }
    public void clearForm() {
        fiyat.setValue("");
        aciklama.setValue("");
        grupKodu.setValue("");
        stokKodu.setValue("");
    }


    public void setStokKartForm(StokKart stokKart) {
        binder.setBean(stokKart);
    }

    private Component buttonsLayout() {
        satinAl.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        cık.addThemeVariants(ButtonVariant.LUMO_ERROR);
        satinAl.addClickShortcut(Key.ENTER);
        cık.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(satinAl,cık);
    }

}
