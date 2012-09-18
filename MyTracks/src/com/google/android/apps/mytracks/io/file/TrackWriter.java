/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.android.apps.mytracks.io.file;

import java.io.File;

/**
 * Implementations of this class export tracks to the SD card.  This class is
 * intended to be format-neutral - it handles creating the output file and
 * reading the track to be exported, but requires an instance of
 * {@link TrackFormatWriter} to actually format the data.
 *
 * @author Sandor Dornbush
 * @author Rodrigo Damazio
 */
public interface TrackWriter {

  /** This listener is used to signal track writes. */
  public interface OnWriteListener {
    /**
     * This method is invoked whenever a location within a track is written.
     * @param number the location number
     * @param max the maximum number of locations, for calculation of
     *     completion percentage
     */
    public void onWrite(int number, int max);
  }

  /**
   * Sets a listener to be invoked for each location writer.
   */
  void setOnWriteListener(OnWriteListener onWriteListener);

  /**
   * Sets a custom directory where the file will be written.
   */
  void setDirectory(File directory);

  /**
   * Returns the absolute path to the file which was created.
   */
  String getAbsolutePath();

  /**
   * Writes the given track id to the SD card.
   * This is blocking.
   */
  void writeTrack();

  /**
   * Stop any in-progress writes
   */
  void stopWriteTrack();

  /**
   * Returns true if the write completed successfully.
   */
  boolean wasSuccess();

  /**
   * Returns the error message (if any) generated by a writer failure.
   */
  int getErrorMessage();
}
