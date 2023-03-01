package org.scienceleadership.filmsgui2023;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FilmsController {
    public TableView allFilmsView;
    public TableColumn<BoxOfficeFilm, Integer> rankColumn;
    public TableColumn<BoxOfficeFilm, String> titleColumn;
    public TableColumn<BoxOfficeFilm, Integer> releaseYearColumn;
    public TableColumn<BoxOfficeFilm, Long> grossColumn;
    public TableColumn<BoxOfficeFilm, Integer> peakColumn;

    public void initialize() {
        //restoreOrReadData();
        BoxOfficeFilm.readAllData();
        ArrayList<Film> temporaryList = (ArrayList<Film>) Film.getAllFilms();
        // Turn the read data's ArrayList into an ObservableList
        ObservableList temporaryObservableList = FXCollections.observableArrayList(temporaryList);
        // Make that ObservableList the list for my TableView
        allFilmsView.setItems(temporaryObservableList);

        // Wire table's columns with BoxOfficeFilm's fields

        // This causes BoxOfficeFilm's values to be displayed in TableView
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        grossColumn.setCellValueFactory(new PropertyValueFactory<>("gross"));
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        peakColumn.setCellValueFactory(new PropertyValueFactory<>("peak"));

        // This causes TableView's values to be editable
        rankColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        grossColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        releaseYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        peakColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        // This causes edited values from TableView to be stored in BoxOfficeFilm object
        rankColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<BoxOfficeFilm, Integer> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    BoxOfficeFilm filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setRank(t.getNewValue());
                });
        titleColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<BoxOfficeFilm, String> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    BoxOfficeFilm filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setTitle(t.getNewValue());
                });
        grossColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<BoxOfficeFilm, Long> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    BoxOfficeFilm filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setGross(t.getNewValue());
                });
        releaseYearColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<BoxOfficeFilm, Integer> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    BoxOfficeFilm filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setReleaseYear(t.getNewValue());
                });
        peakColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<BoxOfficeFilm, Integer> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    BoxOfficeFilm filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setPeak(t.getNewValue());
                });
    }

    public void saveData() throws Exception {
        FileOutputStream fileOut = new FileOutputStream("SavedFilmObjects");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        // allTheTexts is my ListView. Save its ObservableList by turning it into an ArrayList.
        ArrayList<String> temporaryList = new ArrayList<>(allFilmsView.getItems());
        out.writeObject(temporaryList);

        out.close();
        fileOut.close();
    }

    public void restoreOrReadData() {
        // First try to restore saved objects, but then read data file if that fails.
        try {
            FileInputStream fileIn = new FileInputStream("SavedFilmObjects");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // Restored saved objects into Film's arrayList of all films
            Film.setAllFilms((ArrayList<Film>)in.readObject());
            in.close();
            fileIn.close();
        } catch (Exception exception) {
            // Restoring saved objects failed, so read data from text file instead
            BoxOfficeFilm.readAllData();
        }

        ArrayList<Film> temporaryList = (ArrayList<Film>) Film.getAllFilms();
        // Turn the read data's ArrayList into an ObservableList
        ObservableList temporaryObservableList = FXCollections.observableArrayList(temporaryList);
        // Make that ObservableList the list for my TableView
        allFilmsView.setItems(temporaryObservableList);
    }

}