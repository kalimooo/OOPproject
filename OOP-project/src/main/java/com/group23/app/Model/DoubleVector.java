package com.group23.app.Model;

public class DoubleVector {
    private double x, y;

    public DoubleVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DoubleVector() {
        this(0, 0);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double newX) {
        this.x = newX;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public void set(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public DoubleVector VectorDifference(DoubleVector other) {
        return new DoubleVector(other.x - this.x, other.y - this.y);
    }

    public double angleBetween(DoubleVector other) {
        double dotProduct = this.x * other.x + this.y * other.y;
        double magnitudeProduct = Math.sqrt(this.x * this.x + this.y * this.y) * Math.sqrt(other.x * other.x + other.y * other.y);
        return Math.acos(dotProduct / magnitudeProduct);
    }

    public void normalize() {
        double magnitude = Math.sqrt(this.x * this.x + this.y * this.y);
        this.x /= magnitude;
        this.y /= magnitude;
    }

    public DoubleVector add(DoubleVector other) {
        return new DoubleVector(this.x + other.x, this.y + other.y);
    }

    public DoubleVector scalarMultiply(double scalar) {
        return new DoubleVector(this.x * scalar, this.y * scalar);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double dotProduct(DoubleVector other) {
        return this.x * other.x + this.y * other.y;
    }

    public double crossProduct(DoubleVector other) {
        return this.x * other.y - this.y * other.x;
    }

    public double distance(DoubleVector other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public DoubleVector unitVector() {
        double magnitude = this.magnitude();
        return new DoubleVector(this.x / magnitude, this.y / magnitude);
    }

    public void rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double newX = this.x * cos - this.y * sin;
        double newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
    }
}
