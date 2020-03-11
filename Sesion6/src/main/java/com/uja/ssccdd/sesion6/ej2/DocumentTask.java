package com.uja.ssccdd.sesion6.ej2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author José Antonio
 */
public class DocumentTask extends RecursiveTask<Integer> {

    private String[][] document;
    private int start, end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    /**
     * If the task has to process more that ten lines, it divide the block of
     * lines it two subblocks and throws two DocumentTask two process them. In
     * other case, it throws LineTask tasks to process each line of its block
     */
    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 10) {
            result = processLines(document, start, end, word);
        } else {
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Throws a LineTask task for each line of the block of lines this task has
     * to process
     *
     * @param document Document to process
     * @param start Starting position of the block of lines it has to process
     * @param end Finish position of the block of lines it has to process
     * @param word Word we are looking for
     * @return
     */
    private Integer processLines(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<>();

        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        invokeAll(tasks);

        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result = result + task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Method that group the results of two DocumentTask tasks
     *
     * @param number1 Result of the first DocumentTask
     * @param number2 Result of the second DocumentTask
     * @return The sum of the two results
     */
    private Integer groupResults(Integer number1, Integer number2) {
        Integer result;

        result = number1 + number2;
        return result;
    }

}
