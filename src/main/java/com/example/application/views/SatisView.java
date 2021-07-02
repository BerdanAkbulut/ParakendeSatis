package com.example.application.views;


import com.example.application.backend.models.StokKart;
import com.example.application.backend.services.abstracts.SatisService;
import com.example.application.backend.services.abstracts.StokKartService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoThemeDefinition;

@Route("satis")
public class SatisView extends VerticalLayout {

    SatisService satisService;
    private  final  SatisForm form;
    Grid<StokKart> grid = new Grid<>(StokKart.class);
    TextField filter = new TextField("Ürün Ara");
    SplitLayout splitLayout = new SplitLayout();
    private StokKartService stokKartService;

    public SatisView(StokKartService stokKartService,SatisService satisService) {
        setSizeFull();
        setWidthFull();
        getStyle().set("background","url(https://a1industrialtrucks.co.uk/wp-content/uploads/2018/11/background-site-1024x451.jpg)");
        this.stokKartService = stokKartService;
        this.satisService = satisService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureFilter();
         form = new SatisForm(satisService);
         splitLayout.addToPrimary(grid);
         splitLayout.addToSecondary(form);
         splitLayout.setSizeFull();
         splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        // Div content = new Div(grid,form);
       //  content.addClassName("content");
       //  content.setSizeFull();
         
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button btn = new Button("Çıkış Yap", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate(""));
        Button btn2 = new Button("İşlem Raporlarım", VaadinIcon.USER.create(), event-> UI.getCurrent().navigate("satisRapor"));

        horizontalLayout.add(btn,btn2);
        //add(filter,content);
        add(splitLayout,horizontalLayout);
        updateList();
        closeEditor();

    }

    private void closeEditor() {
        form.setStokKartForm(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureFilter() {
        filter.setPlaceholder("Ürün adı giriniz");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e-> updateList());
    }

    private void updateList() {
        grid.setItems(stokKartService.getAll(filter.getValue()));
    }



    private void configureGrid() {

        grid.addClassName("stok-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.setColumns("aciklama","fiyat","grupKodu","stokKodu");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editForm(evt.getValue()));
        
    }

    private void editForm(StokKart value) {
        if (value==null) {
            closeEditor();
        } else {
            form.setStokKartForm(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }


}
