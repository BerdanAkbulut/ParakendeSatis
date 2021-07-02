package com.example.application.views;

import com.example.application.backend.models.Satis;
import com.example.application.backend.models.StokKart;
import com.example.application.backend.services.abstracts.MagazaService;
import com.example.application.backend.services.abstracts.SatisService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("satisRapor")
public class SatisRapor extends VerticalLayout {
    int gelenid = 0;
    private MagazaService magazaService;
    private SatisService satisService;
    Grid<Satis> satisGrid = new Grid<>(Satis.class);
    Binder<Satis> satisBinder = new Binder<>();

    public SatisRapor(MagazaService magazaService, SatisService satisService) {
        gelenid = Integer.parseInt(VaadinSession.getCurrent().getSession().getAttribute("magazaId").toString());
        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");

        this.magazaService = magazaService;
        this.satisService = satisService;

        satisGrid.removeAllColumns();
        satisGrid.setColumns("stokKodu","satisTipi");


       // satisGrid.addComponentColumn(item -> detailBtn(satisGrid,item)).setHeader("Detay");
        satisGrid.setItems(satisService.getByMagaza_Id(gelenid));
        add(new H1("Satın Aldığım Ürünler"),satisGrid);


    }


}
