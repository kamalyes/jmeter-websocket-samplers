/*
 * Copyright © 2023 Kamalyes
 *
 * This file is part of JMeter-WebSocket-Samplers, a JMeter add-on for load-testing WebSocket applications.
 *
 * JMeter-WebSocket-Samplers is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * JMeter-WebSocket-Samplers is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.kamalyes.jmeter.wssampler;

import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

import java.awt.BorderLayout;

public class OpenWebSocketSamplerGui extends AbstractSamplerGui {

    private OpenWebSocketSamplerGuiPanel settingsPanel;

    public OpenWebSocketSamplerGui() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        settingsPanel = new OpenWebSocketSamplerGuiPanel();
        add(settingsPanel, BorderLayout.CENTER);
    }

    @Override
    public void clearGui() {
        super.clearGui();
        settingsPanel.clearGui();
    }

    @Override
    public String getStaticLabel() {
        return "WebSocket Open Connection";
    }

    @Override
    public String getLabelResource() {
        return null;
    }

    @Override
    public TestElement createTestElement() {
        OpenWebSocketSampler element = new OpenWebSocketSampler();
        configureTestElement(element);  // Essential because it sets some basic JMeter properties (e.g. the link between sampler and gui class)
        return element;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof OpenWebSocketSampler) {
            OpenWebSocketSampler sampler = (OpenWebSocketSampler) element;
            settingsPanel.setTLS(sampler.getTLS());
            settingsPanel.serverField.setText(sampler.getServer());
            settingsPanel.portField.setText(sampler.getPort());
            settingsPanel.pathField.setText(sampler.getPath());
            settingsPanel.connectionTimeoutField.setText(sampler.getConnectTimeout());
            settingsPanel.readTimeoutField.setText(sampler.getReadTimeout());
        }
    }

    @Override
    public void modifyTestElement(TestElement element) {
        configureTestElement(element);
        if (element instanceof OpenWebSocketSampler) {
            OpenWebSocketSampler sampler = (OpenWebSocketSampler) element;
            sampler.setTLS(settingsPanel.getTLS());
            sampler.setServer(settingsPanel.serverField.getText());
            sampler.setPort(settingsPanel.portField.getText());
            sampler.setPath(settingsPanel.pathField.getText());
            sampler.setConnectTimeout(settingsPanel.connectionTimeoutField.getText());
            sampler.setReadTimeout(settingsPanel.readTimeoutField.getText());
        }
    }

}
