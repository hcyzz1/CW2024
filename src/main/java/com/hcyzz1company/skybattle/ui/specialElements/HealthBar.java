package com.hcyzz1company.skybattle.ui.specialElements;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

public class HealthBar extends StackPane {

    private ProgressBar progressBar;  // 进度条
    private double maxHealth;  // 最大血量

    public HealthBar(double maxHealth) {
        this.maxHealth = maxHealth;
        this.progressBar = new ProgressBar();
        this.progressBar.setProgress(1.0);  // 初始化时满血
        this.progressBar.setPrefHeight(10);  // 设置血条高度（粗细）
        this.progressBar.setPrefWidth(200);
        this.progressBar.setStyle("-fx-accent: red;");  // 设置血条颜色为红色
        this.getChildren().add(progressBar);
    }

    // 更新血条进度
    public void updateHealth(double currentHealth) {
        double healthPercentage = currentHealth / maxHealth;
        progressBar.setProgress(healthPercentage);
    }

    // 设置血条的位置
    public void updatePosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    // 获取血条的 ProgressBar 对象
    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
