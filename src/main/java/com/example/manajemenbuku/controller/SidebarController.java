package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.Main;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class SidebarController {
    void initSidebarItem(TreeView<String> sidebarTree) {
        TreeItem<String> rootItem = new TreeItem<>("Menu");
        TreeItem<String> dashboardItem = new TreeItem<>("Dashboard");
        rootItem.setExpanded(true);

        TreeItem<String> masterData = new TreeItem<>("Master Data");
        TreeItem<String> anggota = new TreeItem<>("Anggota");
        TreeItem<String> buku = new TreeItem<>("Buku");
        masterData.getChildren().addAll(anggota, buku);
        masterData.setExpanded(true);

        TreeItem<String> transaction = new TreeItem<>("Transaksi");
        TreeItem<String> peminjaman = new TreeItem<>("Peminjaman");
        TreeItem<String> pengembalian = new TreeItem<>("Pengembalian");
        transaction.getChildren().addAll(peminjaman, pengembalian);
        transaction.setExpanded(true);

        rootItem.getChildren().addAll(dashboardItem, masterData, transaction);
        sidebarTree.setRoot(rootItem);
        sidebarTree.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        handleSelected(newValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private void handleSelected(TreeItem<String> selectedItem) throws Exception {
        if (selectedItem != null && selectedItem.isLeaf()) {
            switch (selectedItem.getValue()) {
                case "Dashboard":
                    Main.showDashboard();
                    break;
                case "Anggota":
                    Main.showStudentPage();
                    break;
                case "Buku":
                    Main.showListBooksPage();
                    break;
                default:
                    break;
            }
        }
    }
}
