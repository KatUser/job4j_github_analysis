package ru.job4j.githubanalysis.service.saver;

import java.util.List;

public interface DataSaver <T> {

    void saveData(List<T> data);
}
