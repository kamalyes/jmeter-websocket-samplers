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

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DynamicTitledBorder extends TitledBorder {

    public DynamicTitledBorder(String title) {
        super(title);
    }

    public DynamicTitledBorder(Border border, String title, int left, int defaultPosition) {
        super(border, title, left, defaultPosition);
    }

    public void setEnabled(boolean enabled) {
        Color titleColor = GuiUtils.getLookAndFeelColor("TitledBorder.titleColor");
        Color disabledColor = GuiUtils.getLookAndFeelColor("Label.disabledForeground");
        setTitleColor(enabled? titleColor: disabledColor);
    }
}