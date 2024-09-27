/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

/**
 *
 * @author MarquesV
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Arrays;
import java.util.List;

public class LoginFocusTraversalPolicy extends FocusTraversalPolicy {
    private List<Component> order;

    public LoginFocusTraversalPolicy(Component... components) {
        order = Arrays.asList(components);
    }

    @Override
    public Component getComponentAfter(Container container, Component component) {
        int idx = (order.indexOf(component) + 1) % order.size();
        return order.get(idx);
    }

    @Override
    public Component getComponentBefore(Container container, Component component) {
        int idx = order.indexOf(component) - 1;
        if (idx < 0) {
            idx = order.size() - 1;
        }
        return order.get(idx);
    }

    @Override
    public Component getDefaultComponent(Container container) {
        return order.get(0);
    }

    @Override
    public Component getLastComponent(Container container) {
        return order.get(order.size() - 1);
    }

    @Override
    public Component getFirstComponent(Container container) {
        return order.get(0);
    }
}
