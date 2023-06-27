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

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import io.github.kamalyes.websocket.Frame;
import io.github.kamalyes.websocket.TextFrame;

import static io.github.kamalyes.jmeter.wssampler.ComparisonType.IsPlain;

import java.util.regex.Pattern;

public class TextFrameFilter extends FrameFilter {

    private static Logger log = LoggingManager.getLoggerForClass();

    private Pattern regex = null;

    public TextFrameFilter() {
        setComparisonType(IsPlain);
    }

    @Override
    protected void prepareFilter() {
        if (getComparisonType().isRegexComparison()) {
            regex = Pattern.compile(getMatchValue());
        }
    }

    @Override
    protected boolean matchesFilter(Frame frame) {
        String matchValue = getMatchValue();

        if (frame.isText()) {
            TextFrame receivedFrame = (TextFrame) frame;
            String framePayload = receivedFrame.getText();

            switch (getComparisonType()) {
                case IsPlain:
                    return true;
                case Contains:
                    return matchValue != null && framePayload.contains(matchValue);
                case NotContains:
                    return matchValue != null && !framePayload.contains(matchValue);
                case Equals:
                    return matchValue != null && matchValue.equals(framePayload);
                case NotEquals:
                    return matchValue != null && !matchValue.equals(framePayload);
                case StartsWith:
                    return matchValue != null && framePayload.startsWith(matchValue);
                case NotStartsWith:
                    return matchValue != null && !framePayload.startsWith(matchValue);
                case EndsWith:
                    return matchValue != null && framePayload.endsWith(matchValue);
                case NotEndsWith:
                    return matchValue != null && !framePayload.endsWith(matchValue);
                case ContainsRegex:
                    return matchValue != null && regex.matcher(framePayload).find();
                case NotContainsRegex:
                    return matchValue != null && !regex.matcher(framePayload).find();
                case EqualsRegex:
                    return matchValue != null && regex.matcher(framePayload).matches();
                case NotEqualsRegex:
                    return matchValue != null && !regex.matcher(framePayload).matches();
                default:
                    throw new RuntimeException("unknown comparison type");
            }
        }
        else
            return false;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    public ComparisonType getComparisonType() {
        return ComparisonType.valueOf(getPropertyAsString("comparisonType", "IsPlain"));
    }

    public void setComparisonType(ComparisonType type) {
        setProperty("comparisonType", type.toString());
    }

    public String getMatchValue() {
        return getPropertyAsString("matchValue");
    }

    public void setMatchValue(String value) {
        setProperty("matchValue", value);
    }

}
