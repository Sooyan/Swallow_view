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
 * Data structure for storing spring configuration.
 */
public class SpringConfig {
  public double friction;
  public double tension;

  public static SpringConfig defaultConfig = SpringConfig.fromOrigamiTensionAndFriction(40, 7);

  /**
   * constructor for the SpringConfig
   * @param tension tension value for the SpringConfig
   * @param friction friction value for the SpringConfig
   */
  public SpringConfig(double tension, double friction) {
    this.tension = tension;
    this.friction = friction;
  }

  /**
   * A helper to make creating a SpringConfig easier with values mapping to the Origami values.
   * @param qcTension tension as defined in the Quartz Composition
   * @param qcFriction friction as defined in the Quartz Composition
   * @return a SpringConfig that maps to these values
   */
  public static SpringConfig fromOrigamiTensionAndFriction(double qcTension, double qcFriction) {
    return new SpringConfig(
        OrigamiValueConverter.tensionFromOrigamiValue(qcTension),
        OrigamiValueConverter.frictionFromOrigamiValue(qcFriction)
    );
  }
}
