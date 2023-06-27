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

import org.apache.jmeter.config.gui.AbstractConfigGui;
import org.apache.jmeter.testelement.TestElement;

import java.awt.BorderLayout;

public class PingFrameFilterGui extends AbstractConfigGui {

    private PingFrameFilterGuiPanel settingsPanel;

    public PingFrameFilterGui() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        settingsPanel = new PingFrameFilterGuiPanel();
        add(settingsPanel, BorderLayout.CENTER);
    }

    @Override
    public String getStaticLabel() {
        return "WebSocket Ping/Pong Frame Filter";
    }

    @Override
    public String getLabelResource() {
        return null;
    }

    @Override
    public TestElement createTestElement() {
        FrameFilter frameFilterElement = new PingFrameFilter();
        configureTestElement(frameFilterElement);  // Essential because it sets some basic JMeter properties (e.g. the link between sampler and gui class)
        return frameFilterElement;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof PingFrameFilter) {
            PingFrameFilter filter = (PingFrameFilter) element;
            settingsPanel.replyToPing.setSelected(filter.getReplyToPing());
            settingsPanel.setFilterType(filter.getFilterType());
        }

    }

    @Override
    public void modifyTestElement(TestElement element) {
        configureTestElement(element);
        if (element instanceof PingFrameFilter) {
            PingFrameFilter filter = (PingFrameFilter) element;
            filter.setReplyToPing(settingsPanel.replyToPing.isSelected());
            filter.setFilterType(settingsPanel.getFilterType());
        }
    }

}
