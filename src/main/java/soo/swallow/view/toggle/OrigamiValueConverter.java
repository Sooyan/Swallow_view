/*
 * Copyright 2015 Soo [154014022@qq.com | sootracker@gmail.com]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 */

package soo.swallow.view.toggle;

/**
 * Helper math util to convert tension & friction values from the Origami design tool to values that
 * the spring system needs.
 */
public class OrigamiValueConverter {

  public static double tensionFromOrigamiValue(double oValue) {
    return oValue == 0 ? 0 : (oValue - 30.0) * 3.62 + 194.0;
  }

  public static double origamiValueFromTension(double tension) {
    return tension == 0 ? 0 : (tension - 194.0) / 3.62 + 30.0;
  }

  public static double frictionFromOrigamiValue(double oValue) {
    return oValue == 0 ? 0 : (oValue - 8.0) * 3.0 + 25.0;
  }

  public static double origamiValueFromFriction(double friction) {
    return friction == 0 ? 0 : (friction - 25.0) / 3.0 + 8.0;
  }

}
