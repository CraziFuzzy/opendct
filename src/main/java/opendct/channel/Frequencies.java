/*
 * Copyright 2016 The OpenDCT Authors. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opendct.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Frequencies {
    public final static Frequency US_BCAST[];
    public final static Frequency US_CABLE[];
    public final static Frequency US_HRC[];
    public final static Frequency US_IRC[];
    public final static Frequency EU_BCAST[];
    public final static Frequency EU_CABLE[];

    /**
     * Returns the first channel that corresponds with the requested frequency.
     *
     * @param standard Frequency standard.
     * @param frequency Frequency in Hz.
     * @return The channel that corresponds with the requested frequency. You can get -1 if no
     *         channel was found or no table for the requested standard exists.
     */
    public static int getChannelForFrequency(FrequencyType standard, int frequency) {
        Frequency searchTable[];

        switch (standard) {
            case US_BCAST:
                searchTable = US_BCAST;
                break;
            case US_CABLE:
                searchTable = US_CABLE;
                break;
        	case US_HRC:
                searchTable = US_HRC;
                break;
        	case US_IRC:
                searchTable = US_IRC;
                break;
            case EU_BCAST:
                searchTable = EU_BCAST;
                break;
            case EU_CABLE:
                searchTable = EU_CABLE;
                break;
            default:
                return -1;
        }

        for (Frequency searchFrequency : searchTable) {
            if (searchFrequency != null && frequency == searchFrequency.FREQUENCY) {
                return searchFrequency.CHANNEL;
            }
        }

        return -1;
    }

    /**
     * Get a HashMap with all of the channels mapped to their respective frequencies.
     * <p/>
     * This is to speed up channel scanning by having a list of all of the expected channels that
     * can be checked for within the same frequency.
     * <p/>
     * If there is no frequency associated with a channel it will be added as the frequency 0, so
     * be sure to account for that if this is being used for a channel scan.
     *
     * @param lineup The lineup to be parsed.
     * @return The HashMap with frequencies mapped to the channels they contain.
     */
    public static Map<Integer, ArrayList<TVChannel>> getFrequenciesToChannelsMap(ChannelLineup lineup) {
        if (lineup == null) {
            return null;
        }

        Map<Integer, ArrayList<TVChannel>> returnValues = new HashMap<>(200);

        for (TVChannel tvChannel : lineup.getAllChannels(true, true)) {

            Integer lookupFreq = tvChannel.getFrequency();

            if (tvChannel.getFrequency() <= 0) {
                lookupFreq = 0;
            }

            ArrayList<TVChannel> currentFreq = returnValues.get(lookupFreq);

            if (currentFreq == null) {
                currentFreq = new ArrayList<>();
                returnValues.put(lookupFreq, currentFreq);
            }

            currentFreq.add(tvChannel);
        }

        return returnValues;
    }

    static {
        // Source: https://en.wikipedia.org/wiki/North_American_television_frequencies#Broadcast_television
        // Converted into code on spreadsheet.

        US_BCAST = new Frequency[70];

        US_BCAST[0] = null;
        US_BCAST[1] = null;
        US_BCAST[2] = new Frequency(FrequencyType.US_BCAST, 2, 57000000);
        US_BCAST[3] = new Frequency(FrequencyType.US_BCAST, 3, 63000000);
        US_BCAST[4] = new Frequency(FrequencyType.US_BCAST, 4, 69000000);
        US_BCAST[5] = new Frequency(FrequencyType.US_BCAST, 5, 79000000);
        US_BCAST[6] = new Frequency(FrequencyType.US_BCAST, 6, 85000000);
        US_BCAST[7] = new Frequency(FrequencyType.US_BCAST, 7, 177000000);
        US_BCAST[8] = new Frequency(FrequencyType.US_BCAST, 8, 183000000);
        US_BCAST[9] = new Frequency(FrequencyType.US_BCAST, 9, 189000000);
        US_BCAST[10] = new Frequency(FrequencyType.US_BCAST, 10, 195000000);
        US_BCAST[11] = new Frequency(FrequencyType.US_BCAST, 11, 201000000);
        US_BCAST[12] = new Frequency(FrequencyType.US_BCAST, 12, 207000000);
        US_BCAST[13] = new Frequency(FrequencyType.US_BCAST, 13, 213000000);
        US_BCAST[14] = new Frequency(FrequencyType.US_BCAST, 14, 473000000);
        US_BCAST[15] = new Frequency(FrequencyType.US_BCAST, 15, 479000000);
        US_BCAST[16] = new Frequency(FrequencyType.US_BCAST, 16, 485000000);
        US_BCAST[17] = new Frequency(FrequencyType.US_BCAST, 17, 491000000);
        US_BCAST[18] = new Frequency(FrequencyType.US_BCAST, 18, 497000000);
        US_BCAST[19] = new Frequency(FrequencyType.US_BCAST, 19, 503000000);
        US_BCAST[20] = new Frequency(FrequencyType.US_BCAST, 20, 509000000);
        US_BCAST[21] = new Frequency(FrequencyType.US_BCAST, 21, 515000000);
        US_BCAST[22] = new Frequency(FrequencyType.US_BCAST, 22, 521000000);
        US_BCAST[23] = new Frequency(FrequencyType.US_BCAST, 23, 527000000);
        US_BCAST[24] = new Frequency(FrequencyType.US_BCAST, 24, 533000000);
        US_BCAST[25] = new Frequency(FrequencyType.US_BCAST, 25, 539000000);
        US_BCAST[26] = new Frequency(FrequencyType.US_BCAST, 26, 545000000);
        US_BCAST[27] = new Frequency(FrequencyType.US_BCAST, 27, 551000000);
        US_BCAST[28] = new Frequency(FrequencyType.US_BCAST, 28, 557000000);
        US_BCAST[29] = new Frequency(FrequencyType.US_BCAST, 29, 563000000);
        US_BCAST[30] = new Frequency(FrequencyType.US_BCAST, 30, 569000000);
        US_BCAST[31] = new Frequency(FrequencyType.US_BCAST, 31, 575000000);
        US_BCAST[32] = new Frequency(FrequencyType.US_BCAST, 32, 581000000);
        US_BCAST[33] = new Frequency(FrequencyType.US_BCAST, 33, 587000000);
        US_BCAST[34] = new Frequency(FrequencyType.US_BCAST, 34, 593000000);
        US_BCAST[35] = new Frequency(FrequencyType.US_BCAST, 35, 599000000);
        US_BCAST[36] = new Frequency(FrequencyType.US_BCAST, 36, 605000000);
        US_BCAST[37] = new Frequency(FrequencyType.US_BCAST, 37, 611000000);
        US_BCAST[38] = new Frequency(FrequencyType.US_BCAST, 38, 617000000);
        US_BCAST[39] = new Frequency(FrequencyType.US_BCAST, 39, 623000000);
        US_BCAST[40] = new Frequency(FrequencyType.US_BCAST, 40, 629000000);
        US_BCAST[41] = new Frequency(FrequencyType.US_BCAST, 41, 635000000);
        US_BCAST[42] = new Frequency(FrequencyType.US_BCAST, 42, 641000000);
        US_BCAST[43] = new Frequency(FrequencyType.US_BCAST, 43, 647000000);
        US_BCAST[44] = new Frequency(FrequencyType.US_BCAST, 44, 653000000);
        US_BCAST[45] = new Frequency(FrequencyType.US_BCAST, 45, 659000000);
        US_BCAST[46] = new Frequency(FrequencyType.US_BCAST, 46, 665000000);
        US_BCAST[47] = new Frequency(FrequencyType.US_BCAST, 47, 671000000);
        US_BCAST[48] = new Frequency(FrequencyType.US_BCAST, 48, 677000000);
        US_BCAST[49] = new Frequency(FrequencyType.US_BCAST, 49, 683000000);
        US_BCAST[50] = new Frequency(FrequencyType.US_BCAST, 50, 689000000);
        US_BCAST[51] = new Frequency(FrequencyType.US_BCAST, 51, 695000000);
        US_BCAST[52] = new Frequency(FrequencyType.US_BCAST, 52, 701000000);
        US_BCAST[53] = new Frequency(FrequencyType.US_BCAST, 53, 707000000);
        US_BCAST[54] = new Frequency(FrequencyType.US_BCAST, 54, 713000000);
        US_BCAST[55] = new Frequency(FrequencyType.US_BCAST, 55, 719000000);
        US_BCAST[56] = new Frequency(FrequencyType.US_BCAST, 56, 725000000);
        US_BCAST[57] = new Frequency(FrequencyType.US_BCAST, 57, 731000000);
        US_BCAST[58] = new Frequency(FrequencyType.US_BCAST, 58, 737000000);
        US_BCAST[59] = new Frequency(FrequencyType.US_BCAST, 59, 743000000);
        US_BCAST[60] = new Frequency(FrequencyType.US_BCAST, 60, 749000000);
        US_BCAST[61] = new Frequency(FrequencyType.US_BCAST, 61, 755000000);
        US_BCAST[62] = new Frequency(FrequencyType.US_BCAST, 62, 761000000);
        US_BCAST[63] = new Frequency(FrequencyType.US_BCAST, 63, 767000000);
        US_BCAST[64] = new Frequency(FrequencyType.US_BCAST, 64, 773000000);
        US_BCAST[65] = new Frequency(FrequencyType.US_BCAST, 65, 779000000);
        US_BCAST[66] = new Frequency(FrequencyType.US_BCAST, 66, 785000000);
        US_BCAST[67] = new Frequency(FrequencyType.US_BCAST, 67, 791000000);
        US_BCAST[68] = new Frequency(FrequencyType.US_BCAST, 68, 797000000);
        US_BCAST[69] = new Frequency(FrequencyType.US_BCAST, 69, 803000000);


        US_CABLE = new Frequency[159];

        US_CABLE[0] = null;
        US_CABLE[1] = null;
        US_CABLE[2] = new Frequency(FrequencyType.US_CABLE, 2, 57000000);
        US_CABLE[3] = new Frequency(FrequencyType.US_CABLE, 3, 63000000);
        US_CABLE[4] = new Frequency(FrequencyType.US_CABLE, 4, 69000000);
        US_CABLE[5] = new Frequency(FrequencyType.US_CABLE, 5, 79000000);
        US_CABLE[6] = new Frequency(FrequencyType.US_CABLE, 6, 85000000);
        US_CABLE[7] = new Frequency(FrequencyType.US_CABLE, 7, 177000000);
        US_CABLE[8] = new Frequency(FrequencyType.US_CABLE, 8, 183000000);
        US_CABLE[9] = new Frequency(FrequencyType.US_CABLE, 9, 189000000);
        US_CABLE[10] = new Frequency(FrequencyType.US_CABLE, 10, 195000000);
        US_CABLE[11] = new Frequency(FrequencyType.US_CABLE, 11, 201000000);
        US_CABLE[12] = new Frequency(FrequencyType.US_CABLE, 12, 207000000);
        US_CABLE[13] = new Frequency(FrequencyType.US_CABLE, 13, 213000000);
        US_CABLE[14] = new Frequency(FrequencyType.US_CABLE, 14, 123000000);
        US_CABLE[15] = new Frequency(FrequencyType.US_CABLE, 15, 129000000);
        US_CABLE[16] = new Frequency(FrequencyType.US_CABLE, 16, 135000000);
        US_CABLE[17] = new Frequency(FrequencyType.US_CABLE, 17, 141000000);
        US_CABLE[18] = new Frequency(FrequencyType.US_CABLE, 18, 147000000);
        US_CABLE[19] = new Frequency(FrequencyType.US_CABLE, 19, 153000000);
        US_CABLE[20] = new Frequency(FrequencyType.US_CABLE, 20, 159000000);
        US_CABLE[21] = new Frequency(FrequencyType.US_CABLE, 21, 165000000);
        US_CABLE[22] = new Frequency(FrequencyType.US_CABLE, 22, 171000000);
        US_CABLE[23] = new Frequency(FrequencyType.US_CABLE, 23, 219000000);
        US_CABLE[24] = new Frequency(FrequencyType.US_CABLE, 24, 225000000);
        US_CABLE[25] = new Frequency(FrequencyType.US_CABLE, 25, 231000000);
        US_CABLE[26] = new Frequency(FrequencyType.US_CABLE, 26, 237000000);
        US_CABLE[27] = new Frequency(FrequencyType.US_CABLE, 27, 243000000);
        US_CABLE[28] = new Frequency(FrequencyType.US_CABLE, 28, 249000000);
        US_CABLE[29] = new Frequency(FrequencyType.US_CABLE, 29, 255000000);
        US_CABLE[30] = new Frequency(FrequencyType.US_CABLE, 30, 261000000);
        US_CABLE[31] = new Frequency(FrequencyType.US_CABLE, 31, 267000000);
        US_CABLE[32] = new Frequency(FrequencyType.US_CABLE, 32, 273000000);
        US_CABLE[33] = new Frequency(FrequencyType.US_CABLE, 33, 279000000);
        US_CABLE[34] = new Frequency(FrequencyType.US_CABLE, 34, 285000000);
        US_CABLE[35] = new Frequency(FrequencyType.US_CABLE, 35, 291000000);
        US_CABLE[36] = new Frequency(FrequencyType.US_CABLE, 36, 297000000);
        US_CABLE[37] = new Frequency(FrequencyType.US_CABLE, 37, 303000000);
        US_CABLE[38] = new Frequency(FrequencyType.US_CABLE, 38, 309000000);
        US_CABLE[39] = new Frequency(FrequencyType.US_CABLE, 39, 315000000);
        US_CABLE[40] = new Frequency(FrequencyType.US_CABLE, 40, 321000000);
        US_CABLE[41] = new Frequency(FrequencyType.US_CABLE, 41, 327000000);
        US_CABLE[42] = new Frequency(FrequencyType.US_CABLE, 42, 333000000);
        US_CABLE[43] = new Frequency(FrequencyType.US_CABLE, 43, 339000000);
        US_CABLE[44] = new Frequency(FrequencyType.US_CABLE, 44, 345000000);
        US_CABLE[45] = new Frequency(FrequencyType.US_CABLE, 45, 351000000);
        US_CABLE[46] = new Frequency(FrequencyType.US_CABLE, 46, 357000000);
        US_CABLE[47] = new Frequency(FrequencyType.US_CABLE, 47, 363000000);
        US_CABLE[48] = new Frequency(FrequencyType.US_CABLE, 48, 369000000);
        US_CABLE[49] = new Frequency(FrequencyType.US_CABLE, 49, 375000000);
        US_CABLE[50] = new Frequency(FrequencyType.US_CABLE, 50, 381000000);
        US_CABLE[51] = new Frequency(FrequencyType.US_CABLE, 51, 387000000);
        US_CABLE[52] = new Frequency(FrequencyType.US_CABLE, 52, 393000000);
        US_CABLE[53] = new Frequency(FrequencyType.US_CABLE, 53, 399000000);
        US_CABLE[54] = new Frequency(FrequencyType.US_CABLE, 54, 405000000);
        US_CABLE[55] = new Frequency(FrequencyType.US_CABLE, 55, 411000000);
        US_CABLE[56] = new Frequency(FrequencyType.US_CABLE, 56, 417000000);
        US_CABLE[57] = new Frequency(FrequencyType.US_CABLE, 57, 423000000);
        US_CABLE[58] = new Frequency(FrequencyType.US_CABLE, 58, 429000000);
        US_CABLE[59] = new Frequency(FrequencyType.US_CABLE, 59, 435000000);
        US_CABLE[60] = new Frequency(FrequencyType.US_CABLE, 60, 441000000);
        US_CABLE[61] = new Frequency(FrequencyType.US_CABLE, 61, 447000000);
        US_CABLE[62] = new Frequency(FrequencyType.US_CABLE, 62, 453000000);
        US_CABLE[63] = new Frequency(FrequencyType.US_CABLE, 63, 459000000);
        US_CABLE[64] = new Frequency(FrequencyType.US_CABLE, 64, 465000000);
        US_CABLE[65] = new Frequency(FrequencyType.US_CABLE, 65, 471000000);
        US_CABLE[66] = new Frequency(FrequencyType.US_CABLE, 66, 477000000);
        US_CABLE[67] = new Frequency(FrequencyType.US_CABLE, 67, 483000000);
        US_CABLE[68] = new Frequency(FrequencyType.US_CABLE, 68, 489000000);
        US_CABLE[69] = new Frequency(FrequencyType.US_CABLE, 69, 495000000);
        US_CABLE[70] = new Frequency(FrequencyType.US_CABLE, 70, 501000000);
        US_CABLE[71] = new Frequency(FrequencyType.US_CABLE, 71, 507000000);
        US_CABLE[72] = new Frequency(FrequencyType.US_CABLE, 72, 513000000);
        US_CABLE[73] = new Frequency(FrequencyType.US_CABLE, 73, 519000000);
        US_CABLE[74] = new Frequency(FrequencyType.US_CABLE, 74, 525000000);
        US_CABLE[75] = new Frequency(FrequencyType.US_CABLE, 75, 531000000);
        US_CABLE[76] = new Frequency(FrequencyType.US_CABLE, 76, 537000000);
        US_CABLE[77] = new Frequency(FrequencyType.US_CABLE, 77, 543000000);
        US_CABLE[78] = new Frequency(FrequencyType.US_CABLE, 78, 549000000);
        US_CABLE[79] = new Frequency(FrequencyType.US_CABLE, 79, 555000000);
        US_CABLE[80] = new Frequency(FrequencyType.US_CABLE, 80, 561000000);
        US_CABLE[81] = new Frequency(FrequencyType.US_CABLE, 81, 567000000);
        US_CABLE[82] = new Frequency(FrequencyType.US_CABLE, 82, 573000000);
        US_CABLE[83] = new Frequency(FrequencyType.US_CABLE, 83, 579000000);
        US_CABLE[84] = new Frequency(FrequencyType.US_CABLE, 84, 585000000);
        US_CABLE[85] = new Frequency(FrequencyType.US_CABLE, 85, 591000000);
        US_CABLE[86] = new Frequency(FrequencyType.US_CABLE, 86, 597000000);
        US_CABLE[87] = new Frequency(FrequencyType.US_CABLE, 87, 603000000);
        US_CABLE[88] = new Frequency(FrequencyType.US_CABLE, 88, 609000000);
        US_CABLE[89] = new Frequency(FrequencyType.US_CABLE, 89, 615000000);
        US_CABLE[90] = new Frequency(FrequencyType.US_CABLE, 90, 621000000);
        US_CABLE[91] = new Frequency(FrequencyType.US_CABLE, 91, 627000000);
        US_CABLE[92] = new Frequency(FrequencyType.US_CABLE, 92, 633000000);
        US_CABLE[93] = new Frequency(FrequencyType.US_CABLE, 93, 639000000);
        US_CABLE[94] = new Frequency(FrequencyType.US_CABLE, 94, 645000000);
        US_CABLE[95] = new Frequency(FrequencyType.US_CABLE, 95, 93000000);
        US_CABLE[96] = new Frequency(FrequencyType.US_CABLE, 96, 99000000);
        US_CABLE[97] = new Frequency(FrequencyType.US_CABLE, 97, 105000000);
        US_CABLE[98] = new Frequency(FrequencyType.US_CABLE, 98, 111000000);
        US_CABLE[99] = new Frequency(FrequencyType.US_CABLE, 99, 117000000);
        US_CABLE[100] = new Frequency(FrequencyType.US_CABLE, 100, 651000000);
        US_CABLE[101] = new Frequency(FrequencyType.US_CABLE, 101, 657000000);
        US_CABLE[102] = new Frequency(FrequencyType.US_CABLE, 102, 663000000);
        US_CABLE[103] = new Frequency(FrequencyType.US_CABLE, 103, 669000000);
        US_CABLE[104] = new Frequency(FrequencyType.US_CABLE, 104, 675000000);
        US_CABLE[105] = new Frequency(FrequencyType.US_CABLE, 105, 681000000);
        US_CABLE[106] = new Frequency(FrequencyType.US_CABLE, 106, 687000000);
        US_CABLE[107] = new Frequency(FrequencyType.US_CABLE, 107, 693000000);
        US_CABLE[108] = new Frequency(FrequencyType.US_CABLE, 108, 699000000);
        US_CABLE[109] = new Frequency(FrequencyType.US_CABLE, 109, 705000000);
        US_CABLE[110] = new Frequency(FrequencyType.US_CABLE, 110, 711000000);
        US_CABLE[111] = new Frequency(FrequencyType.US_CABLE, 111, 717000000);
        US_CABLE[112] = new Frequency(FrequencyType.US_CABLE, 112, 723000000);
        US_CABLE[113] = new Frequency(FrequencyType.US_CABLE, 113, 729000000);
        US_CABLE[114] = new Frequency(FrequencyType.US_CABLE, 114, 735000000);
        US_CABLE[115] = new Frequency(FrequencyType.US_CABLE, 115, 741000000);
        US_CABLE[116] = new Frequency(FrequencyType.US_CABLE, 116, 747000000);
        US_CABLE[117] = new Frequency(FrequencyType.US_CABLE, 117, 753000000);
        US_CABLE[118] = new Frequency(FrequencyType.US_CABLE, 118, 759000000);
        US_CABLE[119] = new Frequency(FrequencyType.US_CABLE, 119, 765000000);
        US_CABLE[120] = new Frequency(FrequencyType.US_CABLE, 120, 771000000);
        US_CABLE[121] = new Frequency(FrequencyType.US_CABLE, 121, 777000000);
        US_CABLE[122] = new Frequency(FrequencyType.US_CABLE, 122, 783000000);
        US_CABLE[123] = new Frequency(FrequencyType.US_CABLE, 123, 789000000);
        US_CABLE[124] = new Frequency(FrequencyType.US_CABLE, 124, 795000000);
        US_CABLE[125] = new Frequency(FrequencyType.US_CABLE, 125, 801000000);
        US_CABLE[126] = new Frequency(FrequencyType.US_CABLE, 126, 807000000);
        US_CABLE[127] = new Frequency(FrequencyType.US_CABLE, 127, 813000000);
        US_CABLE[128] = new Frequency(FrequencyType.US_CABLE, 128, 819000000);
        US_CABLE[129] = new Frequency(FrequencyType.US_CABLE, 129, 825000000);
        US_CABLE[130] = new Frequency(FrequencyType.US_CABLE, 130, 831000000);
        US_CABLE[131] = new Frequency(FrequencyType.US_CABLE, 131, 837000000);
        US_CABLE[132] = new Frequency(FrequencyType.US_CABLE, 132, 843000000);
        US_CABLE[133] = new Frequency(FrequencyType.US_CABLE, 133, 849000000);
        US_CABLE[134] = new Frequency(FrequencyType.US_CABLE, 134, 855000000);
        US_CABLE[135] = new Frequency(FrequencyType.US_CABLE, 135, 861000000);
        US_CABLE[136] = new Frequency(FrequencyType.US_CABLE, 136, 867000000);
        US_CABLE[137] = new Frequency(FrequencyType.US_CABLE, 137, 873000000);
        US_CABLE[138] = new Frequency(FrequencyType.US_CABLE, 138, 879000000);
        US_CABLE[139] = new Frequency(FrequencyType.US_CABLE, 139, 885000000);
        US_CABLE[140] = new Frequency(FrequencyType.US_CABLE, 140, 891000000);
        US_CABLE[141] = new Frequency(FrequencyType.US_CABLE, 141, 897000000);
        US_CABLE[142] = new Frequency(FrequencyType.US_CABLE, 142, 903000000);
        US_CABLE[143] = new Frequency(FrequencyType.US_CABLE, 143, 909000000);
        US_CABLE[144] = new Frequency(FrequencyType.US_CABLE, 144, 915000000);
        US_CABLE[145] = new Frequency(FrequencyType.US_CABLE, 145, 921000000);
        US_CABLE[146] = new Frequency(FrequencyType.US_CABLE, 146, 927000000);
        US_CABLE[147] = new Frequency(FrequencyType.US_CABLE, 147, 933000000);
        US_CABLE[148] = new Frequency(FrequencyType.US_CABLE, 148, 939000000);
        US_CABLE[149] = new Frequency(FrequencyType.US_CABLE, 149, 945000000);
        US_CABLE[150] = new Frequency(FrequencyType.US_CABLE, 150, 951000000);
        US_CABLE[151] = new Frequency(FrequencyType.US_CABLE, 151, 957000000);
        US_CABLE[152] = new Frequency(FrequencyType.US_CABLE, 152, 963000000);
        US_CABLE[153] = new Frequency(FrequencyType.US_CABLE, 153, 969000000);
        US_CABLE[154] = new Frequency(FrequencyType.US_CABLE, 154, 975000000);
        US_CABLE[155] = new Frequency(FrequencyType.US_CABLE, 155, 981000000);
        US_CABLE[156] = new Frequency(FrequencyType.US_CABLE, 156, 987000000);
        US_CABLE[157] = new Frequency(FrequencyType.US_CABLE, 157, 993000000);
        US_CABLE[158] = new Frequency(FrequencyType.US_CABLE, 158, 999000000);

        US_HRC = new Frequency[159];

        US_HRC[0] = null;
        US_HRC[1] = null;
        US_HRC[2] = new Frequency(FrequencyType.US_HRC, 2, 55752700);
        US_HRC[3] = new Frequency(FrequencyType.US_HRC, 3, 61753000);
        US_HRC[4] = new Frequency(FrequencyType.US_HRC, 4, 67753300);
        US_HRC[5] = new Frequency(FrequencyType.US_HRC, 5, 79753900);
        US_HRC[6] = new Frequency(FrequencyType.US_HRC, 6, 85754200);
        US_HRC[7] = new Frequency(FrequencyType.US_HRC, 7, 175758700);
        US_HRC[8] = new Frequency(FrequencyType.US_HRC, 8, 181759000);
        US_HRC[9] = new Frequency(FrequencyType.US_HRC, 9, 187759300);
        US_HRC[10] = new Frequency(FrequencyType.US_HRC, 10, 193759600);
        US_HRC[11] = new Frequency(FrequencyType.US_HRC, 11, 199759900);
        US_HRC[12] = new Frequency(FrequencyType.US_HRC, 12, 205760200);
        US_HRC[13] = new Frequency(FrequencyType.US_HRC, 13, 211760500);
        US_HRC[14] = new Frequency(FrequencyType.US_HRC, 14, 121756000);
        US_HRC[15] = new Frequency(FrequencyType.US_HRC, 15, 127756300);
        US_HRC[16] = new Frequency(FrequencyType.US_HRC, 16, 133756600);
        US_HRC[17] = new Frequency(FrequencyType.US_HRC, 17, 139756900);
        US_HRC[18] = new Frequency(FrequencyType.US_HRC, 18, 145757200);
        US_HRC[19] = new Frequency(FrequencyType.US_HRC, 19, 151757500);
        US_HRC[20] = new Frequency(FrequencyType.US_HRC, 20, 157757800);
        US_HRC[21] = new Frequency(FrequencyType.US_HRC, 21, 163758100);
        US_HRC[22] = new Frequency(FrequencyType.US_HRC, 22, 169758400);
        US_HRC[23] = new Frequency(FrequencyType.US_HRC, 23, 217760800);
        US_HRC[24] = new Frequency(FrequencyType.US_HRC, 24, 223761100);
        US_HRC[25] = new Frequency(FrequencyType.US_HRC, 25, 229761400);
        US_HRC[26] = new Frequency(FrequencyType.US_HRC, 26, 235761700);
        US_HRC[27] = new Frequency(FrequencyType.US_HRC, 27, 241762000);
        US_HRC[28] = new Frequency(FrequencyType.US_HRC, 28, 247762300);
        US_HRC[29] = new Frequency(FrequencyType.US_HRC, 29, 253762600);
        US_HRC[30] = new Frequency(FrequencyType.US_HRC, 30, 259762900);
        US_HRC[31] = new Frequency(FrequencyType.US_HRC, 31, 265763200);
        US_HRC[32] = new Frequency(FrequencyType.US_HRC, 32, 271763500);
        US_HRC[33] = new Frequency(FrequencyType.US_HRC, 33, 277763800);
        US_HRC[34] = new Frequency(FrequencyType.US_HRC, 34, 283764100);
        US_HRC[35] = new Frequency(FrequencyType.US_HRC, 35, 289764400);
        US_HRC[36] = new Frequency(FrequencyType.US_HRC, 36, 295764700);
        US_HRC[37] = new Frequency(FrequencyType.US_HRC, 37, 301765000);
        US_HRC[38] = new Frequency(FrequencyType.US_HRC, 38, 307765300);
        US_HRC[39] = new Frequency(FrequencyType.US_HRC, 39, 313765600);
        US_HRC[40] = new Frequency(FrequencyType.US_HRC, 40, 319765900);
        US_HRC[41] = new Frequency(FrequencyType.US_HRC, 41, 325766200);
        US_HRC[42] = new Frequency(FrequencyType.US_HRC, 42, 331766500);
        US_HRC[43] = new Frequency(FrequencyType.US_HRC, 43, 337766800);
        US_HRC[44] = new Frequency(FrequencyType.US_HRC, 44, 343767100);
        US_HRC[45] = new Frequency(FrequencyType.US_HRC, 45, 349767400);
        US_HRC[46] = new Frequency(FrequencyType.US_HRC, 46, 355767700);
        US_HRC[47] = new Frequency(FrequencyType.US_HRC, 47, 361768000);
        US_HRC[48] = new Frequency(FrequencyType.US_HRC, 48, 367768300);
        US_HRC[49] = new Frequency(FrequencyType.US_HRC, 49, 373768600);
        US_HRC[50] = new Frequency(FrequencyType.US_HRC, 50, 379768900);
        US_HRC[51] = new Frequency(FrequencyType.US_HRC, 51, 385769200);
        US_HRC[52] = new Frequency(FrequencyType.US_HRC, 52, 391769500);
        US_HRC[53] = new Frequency(FrequencyType.US_HRC, 53, 397769800);
        US_HRC[54] = new Frequency(FrequencyType.US_HRC, 54, 403770100);
        US_HRC[55] = new Frequency(FrequencyType.US_HRC, 55, 409770400);
        US_HRC[56] = new Frequency(FrequencyType.US_HRC, 56, 415770700);
        US_HRC[57] = new Frequency(FrequencyType.US_HRC, 57, 421771000);
        US_HRC[58] = new Frequency(FrequencyType.US_HRC, 58, 427771300);
        US_HRC[59] = new Frequency(FrequencyType.US_HRC, 59, 433771600);
        US_HRC[60] = new Frequency(FrequencyType.US_HRC, 60, 439771900);
        US_HRC[61] = new Frequency(FrequencyType.US_HRC, 61, 445772200);
        US_HRC[62] = new Frequency(FrequencyType.US_HRC, 62, 451772500);
        US_HRC[63] = new Frequency(FrequencyType.US_HRC, 63, 457772800);
        US_HRC[64] = new Frequency(FrequencyType.US_HRC, 64, 463773100);
        US_HRC[65] = new Frequency(FrequencyType.US_HRC, 65, 469773400);
        US_HRC[66] = new Frequency(FrequencyType.US_HRC, 66, 475773700);
        US_HRC[67] = new Frequency(FrequencyType.US_HRC, 67, 481774000);
        US_HRC[68] = new Frequency(FrequencyType.US_HRC, 68, 487774300);
        US_HRC[69] = new Frequency(FrequencyType.US_HRC, 69, 493774600);
        US_HRC[70] = new Frequency(FrequencyType.US_HRC, 70, 499774900);
        US_HRC[71] = new Frequency(FrequencyType.US_HRC, 71, 505775200);
        US_HRC[72] = new Frequency(FrequencyType.US_HRC, 72, 511775500);
        US_HRC[73] = new Frequency(FrequencyType.US_HRC, 73, 517775800);
        US_HRC[74] = new Frequency(FrequencyType.US_HRC, 74, 523776100);
        US_HRC[75] = new Frequency(FrequencyType.US_HRC, 75, 529776400);
        US_HRC[76] = new Frequency(FrequencyType.US_HRC, 76, 535776700);
        US_HRC[77] = new Frequency(FrequencyType.US_HRC, 77, 541777000);
        US_HRC[78] = new Frequency(FrequencyType.US_HRC, 78, 547777300);
        US_HRC[79] = new Frequency(FrequencyType.US_HRC, 79, 553777600);
        US_HRC[80] = new Frequency(FrequencyType.US_HRC, 80, 559777900);
        US_HRC[81] = new Frequency(FrequencyType.US_HRC, 81, 565778200);
        US_HRC[82] = new Frequency(FrequencyType.US_HRC, 82, 571778500);
        US_HRC[83] = new Frequency(FrequencyType.US_HRC, 83, 577778800);
        US_HRC[84] = new Frequency(FrequencyType.US_HRC, 84, 583779100);
        US_HRC[85] = new Frequency(FrequencyType.US_HRC, 85, 589779400);
        US_HRC[86] = new Frequency(FrequencyType.US_HRC, 86, 595779700);
        US_HRC[87] = new Frequency(FrequencyType.US_HRC, 87, 601780000);
        US_HRC[88] = new Frequency(FrequencyType.US_HRC, 88, 607780300);
        US_HRC[89] = new Frequency(FrequencyType.US_HRC, 89, 613780600);
        US_HRC[90] = new Frequency(FrequencyType.US_HRC, 90, 619780900);
        US_HRC[91] = new Frequency(FrequencyType.US_HRC, 91, 625781200);
        US_HRC[92] = new Frequency(FrequencyType.US_HRC, 92, 631781500);
        US_HRC[93] = new Frequency(FrequencyType.US_HRC, 93, 637781800);
        US_HRC[94] = new Frequency(FrequencyType.US_HRC, 94, 643782100);
        US_HRC[95] = new Frequency(FrequencyType.US_HRC, 95, 91754500);
        US_HRC[96] = new Frequency(FrequencyType.US_HRC, 96, 97754800);
        US_HRC[97] = new Frequency(FrequencyType.US_HRC, 97, 103755100);
        US_HRC[98] = new Frequency(FrequencyType.US_HRC, 98, 109755400);
        US_HRC[99] = new Frequency(FrequencyType.US_HRC, 99, 115755700);
        US_HRC[100] = new Frequency(FrequencyType.US_HRC, 100, 649782400);
        US_HRC[101] = new Frequency(FrequencyType.US_HRC, 101, 655782700);
        US_HRC[102] = new Frequency(FrequencyType.US_HRC, 102, 661783000);
        US_HRC[103] = new Frequency(FrequencyType.US_HRC, 103, 667783300);
        US_HRC[104] = new Frequency(FrequencyType.US_HRC, 104, 673783600);
        US_HRC[105] = new Frequency(FrequencyType.US_HRC, 105, 679783900);
        US_HRC[106] = new Frequency(FrequencyType.US_HRC, 106, 685784200);
        US_HRC[107] = new Frequency(FrequencyType.US_HRC, 107, 691784500);
        US_HRC[108] = new Frequency(FrequencyType.US_HRC, 108, 697784800);
        US_HRC[109] = new Frequency(FrequencyType.US_HRC, 109, 703785100);
        US_HRC[110] = new Frequency(FrequencyType.US_HRC, 110, 709785400);
        US_HRC[111] = new Frequency(FrequencyType.US_HRC, 111, 715785700);
        US_HRC[112] = new Frequency(FrequencyType.US_HRC, 112, 721786000);
        US_HRC[113] = new Frequency(FrequencyType.US_HRC, 113, 727786300);
        US_HRC[114] = new Frequency(FrequencyType.US_HRC, 114, 733786600);
        US_HRC[115] = new Frequency(FrequencyType.US_HRC, 115, 739786900);
        US_HRC[116] = new Frequency(FrequencyType.US_HRC, 116, 745787200);
        US_HRC[117] = new Frequency(FrequencyType.US_HRC, 117, 751787500);
        US_HRC[118] = new Frequency(FrequencyType.US_HRC, 118, 757787800);
        US_HRC[119] = new Frequency(FrequencyType.US_HRC, 119, 763788100);
        US_HRC[120] = new Frequency(FrequencyType.US_HRC, 120, 769788400);
        US_HRC[121] = new Frequency(FrequencyType.US_HRC, 121, 775788700);
        US_HRC[122] = new Frequency(FrequencyType.US_HRC, 122, 781789000);
        US_HRC[123] = new Frequency(FrequencyType.US_HRC, 123, 787789300);
        US_HRC[124] = new Frequency(FrequencyType.US_HRC, 124, 793789600);
        US_HRC[125] = new Frequency(FrequencyType.US_HRC, 125, 799789900);
        US_HRC[126] = new Frequency(FrequencyType.US_HRC, 126, 805790200);
        US_HRC[127] = new Frequency(FrequencyType.US_HRC, 127, 811790500);
        US_HRC[128] = new Frequency(FrequencyType.US_HRC, 128, 817790800);
        US_HRC[129] = new Frequency(FrequencyType.US_HRC, 129, 823791100);
        US_HRC[130] = new Frequency(FrequencyType.US_HRC, 130, 829791400);
        US_HRC[131] = new Frequency(FrequencyType.US_HRC, 131, 835791700);
        US_HRC[132] = new Frequency(FrequencyType.US_HRC, 132, 841792000);
        US_HRC[133] = new Frequency(FrequencyType.US_HRC, 133, 847792300);
        US_HRC[134] = new Frequency(FrequencyType.US_HRC, 134, 853792600);
        US_HRC[135] = new Frequency(FrequencyType.US_HRC, 135, 859792900);
        US_HRC[136] = new Frequency(FrequencyType.US_HRC, 136, 865793200);
        US_HRC[137] = new Frequency(FrequencyType.US_HRC, 137, 871793500);
        US_HRC[138] = new Frequency(FrequencyType.US_HRC, 138, 877793800);
        US_HRC[139] = new Frequency(FrequencyType.US_HRC, 139, 883794100);
        US_HRC[140] = new Frequency(FrequencyType.US_HRC, 140, 889794400);
        US_HRC[141] = new Frequency(FrequencyType.US_HRC, 141, 895794700);
        US_HRC[142] = new Frequency(FrequencyType.US_HRC, 142, 901795000);
        US_HRC[143] = new Frequency(FrequencyType.US_HRC, 143, 907795300);
        US_HRC[144] = new Frequency(FrequencyType.US_HRC, 144, 913795600);
        US_HRC[145] = new Frequency(FrequencyType.US_HRC, 145, 919795900);
        US_HRC[146] = new Frequency(FrequencyType.US_HRC, 146, 925796200);
        US_HRC[147] = new Frequency(FrequencyType.US_HRC, 147, 931796500);
        US_HRC[148] = new Frequency(FrequencyType.US_HRC, 148, 937796800);
        US_HRC[149] = new Frequency(FrequencyType.US_HRC, 149, 943797100);
        US_HRC[150] = new Frequency(FrequencyType.US_HRC, 150, 949797400);
        US_HRC[151] = new Frequency(FrequencyType.US_HRC, 151, 955797700);
        US_HRC[152] = new Frequency(FrequencyType.US_HRC, 152, 961798000);
        US_HRC[153] = new Frequency(FrequencyType.US_HRC, 153, 967798300);
        US_HRC[154] = new Frequency(FrequencyType.US_HRC, 154, 973798600);
        US_HRC[155] = new Frequency(FrequencyType.US_HRC, 155, 979798900);
        US_HRC[156] = new Frequency(FrequencyType.US_HRC, 156, 985799200);
        US_HRC[157] = new Frequency(FrequencyType.US_HRC, 157, 991799500);
        US_HRC[158] = new Frequency(FrequencyType.US_HRC, 158, 997799800);

        US_IRC = new Frequency[159];

        US_IRC[0] = null;
        US_IRC[1] = null;
        US_IRC[2] = new Frequency(FrequencyType.US_IRC, 2, 57000000);
        US_IRC[3] = new Frequency(FrequencyType.US_IRC, 3, 63000000);
        US_IRC[4] = new Frequency(FrequencyType.US_IRC, 4, 69000000);
        US_IRC[5] = new Frequency(FrequencyType.US_IRC, 5, 177000000);
        US_IRC[6] = new Frequency(FrequencyType.US_IRC, 6, 183000000);
        US_IRC[7] = new Frequency(FrequencyType.US_IRC, 7, 189000000);
        US_IRC[8] = new Frequency(FrequencyType.US_IRC, 8, 195000000);
        US_IRC[9] = new Frequency(FrequencyType.US_IRC, 9, 201000000);
        US_IRC[10] = new Frequency(FrequencyType.US_IRC, 10, 207000000);
        US_IRC[11] = new Frequency(FrequencyType.US_IRC, 11, 213000000);
        US_IRC[12] = new Frequency(FrequencyType.US_IRC, 12, 219000000);
        US_IRC[13] = new Frequency(FrequencyType.US_IRC, 13, 225000000);
        US_IRC[14] = new Frequency(FrequencyType.US_IRC, 14, 123000000);
        US_IRC[15] = new Frequency(FrequencyType.US_IRC, 15, 129000000);
        US_IRC[16] = new Frequency(FrequencyType.US_IRC, 16, 135000000);
        US_IRC[17] = new Frequency(FrequencyType.US_IRC, 17, 141000000);
        US_IRC[18] = new Frequency(FrequencyType.US_IRC, 18, 147000000);
        US_IRC[19] = new Frequency(FrequencyType.US_IRC, 19, 153000000);
        US_IRC[20] = new Frequency(FrequencyType.US_IRC, 20, 159000000);
        US_IRC[21] = new Frequency(FrequencyType.US_IRC, 21, 165000000);
        US_IRC[22] = new Frequency(FrequencyType.US_IRC, 22, 171000000);
        US_IRC[23] = new Frequency(FrequencyType.US_IRC, 23, 219000000);
        US_IRC[24] = new Frequency(FrequencyType.US_IRC, 24, 225000000);
        US_IRC[25] = new Frequency(FrequencyType.US_IRC, 25, 231000000);
        US_IRC[26] = new Frequency(FrequencyType.US_IRC, 26, 237000000);
        US_IRC[27] = new Frequency(FrequencyType.US_IRC, 27, 243000000);
        US_IRC[28] = new Frequency(FrequencyType.US_IRC, 28, 249000000);
        US_IRC[29] = new Frequency(FrequencyType.US_IRC, 29, 255000000);
        US_IRC[30] = new Frequency(FrequencyType.US_IRC, 30, 261000000);
        US_IRC[31] = new Frequency(FrequencyType.US_IRC, 31, 267000000);
        US_IRC[32] = new Frequency(FrequencyType.US_IRC, 32, 273000000);
        US_IRC[33] = new Frequency(FrequencyType.US_IRC, 33, 279000000);
        US_IRC[34] = new Frequency(FrequencyType.US_IRC, 34, 285000000);
        US_IRC[35] = new Frequency(FrequencyType.US_IRC, 35, 291000000);
        US_IRC[36] = new Frequency(FrequencyType.US_IRC, 36, 297000000);
        US_IRC[37] = new Frequency(FrequencyType.US_IRC, 37, 303000000);
        US_IRC[38] = new Frequency(FrequencyType.US_IRC, 38, 309000000);
        US_IRC[39] = new Frequency(FrequencyType.US_IRC, 39, 315000000);
        US_IRC[40] = new Frequency(FrequencyType.US_IRC, 40, 321000000);
        US_IRC[41] = new Frequency(FrequencyType.US_IRC, 41, 327000000);
        US_IRC[42] = new Frequency(FrequencyType.US_IRC, 42, 333000000);
        US_IRC[43] = new Frequency(FrequencyType.US_IRC, 43, 339000000);
        US_IRC[44] = new Frequency(FrequencyType.US_IRC, 44, 345000000);
        US_IRC[45] = new Frequency(FrequencyType.US_IRC, 45, 351000000);
        US_IRC[46] = new Frequency(FrequencyType.US_IRC, 46, 357000000);
        US_IRC[47] = new Frequency(FrequencyType.US_IRC, 47, 363000000);
        US_IRC[48] = new Frequency(FrequencyType.US_IRC, 48, 369000000);
        US_IRC[49] = new Frequency(FrequencyType.US_IRC, 49, 375000000);
        US_IRC[50] = new Frequency(FrequencyType.US_IRC, 50, 381000000);
        US_IRC[51] = new Frequency(FrequencyType.US_IRC, 51, 387000000);
        US_IRC[52] = new Frequency(FrequencyType.US_IRC, 52, 393000000);
        US_IRC[53] = new Frequency(FrequencyType.US_IRC, 53, 399000000);
        US_IRC[54] = new Frequency(FrequencyType.US_IRC, 54, 405000000);
        US_IRC[55] = new Frequency(FrequencyType.US_IRC, 55, 411000000);
        US_IRC[56] = new Frequency(FrequencyType.US_IRC, 56, 417000000);
        US_IRC[57] = new Frequency(FrequencyType.US_IRC, 57, 423000000);
        US_IRC[58] = new Frequency(FrequencyType.US_IRC, 58, 429000000);
        US_IRC[59] = new Frequency(FrequencyType.US_IRC, 59, 435000000);
        US_IRC[60] = new Frequency(FrequencyType.US_IRC, 60, 441000000);
        US_IRC[61] = new Frequency(FrequencyType.US_IRC, 61, 447000000);
        US_IRC[62] = new Frequency(FrequencyType.US_IRC, 62, 453000000);
        US_IRC[63] = new Frequency(FrequencyType.US_IRC, 63, 459000000);
        US_IRC[64] = new Frequency(FrequencyType.US_IRC, 64, 465000000);
        US_IRC[65] = new Frequency(FrequencyType.US_IRC, 65, 471000000);
        US_IRC[66] = new Frequency(FrequencyType.US_IRC, 66, 477000000);
        US_IRC[67] = new Frequency(FrequencyType.US_IRC, 67, 483000000);
        US_IRC[68] = new Frequency(FrequencyType.US_IRC, 68, 489000000);
        US_IRC[69] = new Frequency(FrequencyType.US_IRC, 69, 495000000);
        US_IRC[70] = new Frequency(FrequencyType.US_IRC, 70, 501000000);
        US_IRC[71] = new Frequency(FrequencyType.US_IRC, 71, 507000000);
        US_IRC[72] = new Frequency(FrequencyType.US_IRC, 72, 513000000);
        US_IRC[73] = new Frequency(FrequencyType.US_IRC, 73, 519000000);
        US_IRC[74] = new Frequency(FrequencyType.US_IRC, 74, 525000000);
        US_IRC[75] = new Frequency(FrequencyType.US_IRC, 75, 531000000);
        US_IRC[76] = new Frequency(FrequencyType.US_IRC, 76, 537000000);
        US_IRC[77] = new Frequency(FrequencyType.US_IRC, 77, 543000000);
        US_IRC[78] = new Frequency(FrequencyType.US_IRC, 78, 549000000);
        US_IRC[79] = new Frequency(FrequencyType.US_IRC, 79, 555000000);
        US_IRC[80] = new Frequency(FrequencyType.US_IRC, 80, 561000000);
        US_IRC[81] = new Frequency(FrequencyType.US_IRC, 81, 567000000);
        US_IRC[82] = new Frequency(FrequencyType.US_IRC, 82, 573000000);
        US_IRC[83] = new Frequency(FrequencyType.US_IRC, 83, 579000000);
        US_IRC[84] = new Frequency(FrequencyType.US_IRC, 84, 585000000);
        US_IRC[85] = new Frequency(FrequencyType.US_IRC, 85, 591000000);
        US_IRC[86] = new Frequency(FrequencyType.US_IRC, 86, 597000000);
        US_IRC[87] = new Frequency(FrequencyType.US_IRC, 87, 603000000);
        US_IRC[88] = new Frequency(FrequencyType.US_IRC, 88, 609000000);
        US_IRC[89] = new Frequency(FrequencyType.US_IRC, 89, 615000000);
        US_IRC[90] = new Frequency(FrequencyType.US_IRC, 90, 621000000);
        US_IRC[91] = new Frequency(FrequencyType.US_IRC, 91, 627000000);
        US_IRC[92] = new Frequency(FrequencyType.US_IRC, 92, 633000000);
        US_IRC[93] = new Frequency(FrequencyType.US_IRC, 93, 639000000);
        US_IRC[94] = new Frequency(FrequencyType.US_IRC, 94, 645000000);
        US_IRC[95] = new Frequency(FrequencyType.US_IRC, 95, 93000000);
        US_IRC[96] = new Frequency(FrequencyType.US_IRC, 96, 99000000);
        US_IRC[97] = new Frequency(FrequencyType.US_IRC, 97, 105000000);
        US_IRC[98] = new Frequency(FrequencyType.US_IRC, 98, 111000000);
        US_IRC[99] = new Frequency(FrequencyType.US_IRC, 99, 117000000);
        US_IRC[100] = new Frequency(FrequencyType.US_IRC, 100, 651000000);
        US_IRC[101] = new Frequency(FrequencyType.US_IRC, 101, 657000000);
        US_IRC[102] = new Frequency(FrequencyType.US_IRC, 102, 663000000);
        US_IRC[103] = new Frequency(FrequencyType.US_IRC, 103, 669000000);
        US_IRC[104] = new Frequency(FrequencyType.US_IRC, 104, 675000000);
        US_IRC[105] = new Frequency(FrequencyType.US_IRC, 105, 681000000);
        US_IRC[106] = new Frequency(FrequencyType.US_IRC, 106, 687000000);
        US_IRC[107] = new Frequency(FrequencyType.US_IRC, 107, 693000000);
        US_IRC[108] = new Frequency(FrequencyType.US_IRC, 108, 699000000);
        US_IRC[109] = new Frequency(FrequencyType.US_IRC, 109, 705000000);
        US_IRC[110] = new Frequency(FrequencyType.US_IRC, 110, 711000000);
        US_IRC[111] = new Frequency(FrequencyType.US_IRC, 111, 717000000);
        US_IRC[112] = new Frequency(FrequencyType.US_IRC, 112, 723000000);
        US_IRC[113] = new Frequency(FrequencyType.US_IRC, 113, 729000000);
        US_IRC[114] = new Frequency(FrequencyType.US_IRC, 114, 735000000);
        US_IRC[115] = new Frequency(FrequencyType.US_IRC, 115, 741000000);
        US_IRC[116] = new Frequency(FrequencyType.US_IRC, 116, 747000000);
        US_IRC[117] = new Frequency(FrequencyType.US_IRC, 117, 753000000);
        US_IRC[118] = new Frequency(FrequencyType.US_IRC, 118, 759000000);
        US_IRC[119] = new Frequency(FrequencyType.US_IRC, 119, 765000000);
        US_IRC[120] = new Frequency(FrequencyType.US_IRC, 120, 771000000);
        US_IRC[121] = new Frequency(FrequencyType.US_IRC, 121, 777000000);
        US_IRC[122] = new Frequency(FrequencyType.US_IRC, 122, 783000000);
        US_IRC[123] = new Frequency(FrequencyType.US_IRC, 123, 789000000);
        US_IRC[124] = new Frequency(FrequencyType.US_IRC, 124, 795000000);
        US_IRC[125] = new Frequency(FrequencyType.US_IRC, 125, 801000000);
        US_IRC[126] = new Frequency(FrequencyType.US_IRC, 126, 807000000);
        US_IRC[127] = new Frequency(FrequencyType.US_IRC, 127, 813000000);
        US_IRC[128] = new Frequency(FrequencyType.US_IRC, 128, 819000000);
        US_IRC[129] = new Frequency(FrequencyType.US_IRC, 129, 825000000);
        US_IRC[130] = new Frequency(FrequencyType.US_IRC, 130, 831000000);
        US_IRC[131] = new Frequency(FrequencyType.US_IRC, 131, 837000000);
        US_IRC[132] = new Frequency(FrequencyType.US_IRC, 132, 843000000);
        US_IRC[133] = new Frequency(FrequencyType.US_IRC, 133, 849000000);
        US_IRC[134] = new Frequency(FrequencyType.US_IRC, 134, 855000000);
        US_IRC[135] = new Frequency(FrequencyType.US_IRC, 135, 861000000);
        US_IRC[136] = new Frequency(FrequencyType.US_IRC, 136, 867000000);
        US_IRC[137] = new Frequency(FrequencyType.US_IRC, 137, 873000000);
        US_IRC[138] = new Frequency(FrequencyType.US_IRC, 138, 879000000);
        US_IRC[139] = new Frequency(FrequencyType.US_IRC, 139, 885000000);
        US_IRC[140] = new Frequency(FrequencyType.US_IRC, 140, 891000000);
        US_IRC[141] = new Frequency(FrequencyType.US_IRC, 141, 897000000);
        US_IRC[142] = new Frequency(FrequencyType.US_IRC, 142, 903000000);
        US_IRC[143] = new Frequency(FrequencyType.US_IRC, 143, 909000000);
        US_IRC[144] = new Frequency(FrequencyType.US_IRC, 144, 915000000);
        US_IRC[145] = new Frequency(FrequencyType.US_IRC, 145, 921000000);
        US_IRC[146] = new Frequency(FrequencyType.US_IRC, 146, 927000000);
        US_IRC[147] = new Frequency(FrequencyType.US_IRC, 147, 933000000);
        US_IRC[148] = new Frequency(FrequencyType.US_IRC, 148, 939000000);
        US_IRC[149] = new Frequency(FrequencyType.US_IRC, 149, 945000000);
        US_IRC[150] = new Frequency(FrequencyType.US_IRC, 150, 951000000);
        US_IRC[151] = new Frequency(FrequencyType.US_IRC, 151, 957000000);
        US_IRC[152] = new Frequency(FrequencyType.US_IRC, 152, 963000000);
        US_IRC[153] = new Frequency(FrequencyType.US_IRC, 153, 969000000);
        US_IRC[154] = new Frequency(FrequencyType.US_IRC, 154, 975000000);
        US_IRC[155] = new Frequency(FrequencyType.US_IRC, 155, 981000000);
        US_IRC[156] = new Frequency(FrequencyType.US_IRC, 156, 987000000);
        US_IRC[157] = new Frequency(FrequencyType.US_IRC, 157, 993000000);
        US_IRC[158] = new Frequency(FrequencyType.US_IRC, 158, 999000000);
        
        EU_BCAST = new Frequency[70];

        EU_BCAST[0] = null;
        EU_BCAST[1] = null;
        EU_BCAST[2] = null;
        EU_BCAST[3] = null;
        EU_BCAST[4] = null;
        EU_BCAST[5] = new Frequency(FrequencyType.EU_BCAST, 5, 177500000);
        EU_BCAST[6] = new Frequency(FrequencyType.EU_BCAST, 6, 184500000);
        EU_BCAST[7] = new Frequency(FrequencyType.EU_BCAST, 7, 191500000);
        EU_BCAST[8] = new Frequency(FrequencyType.EU_BCAST, 8, 198500000);
        EU_BCAST[9] = new Frequency(FrequencyType.EU_BCAST, 9, 205500000);
        EU_BCAST[10] = new Frequency(FrequencyType.EU_BCAST, 10, 212500000);
        EU_BCAST[11] = new Frequency(FrequencyType.EU_BCAST, 11, 219500000);
        EU_BCAST[12] = new Frequency(FrequencyType.EU_BCAST, 12, 226500000);
        EU_BCAST[13] = null;
        EU_BCAST[14] = null;
        EU_BCAST[15] = null;
        EU_BCAST[16] = null;
        EU_BCAST[17] = null;
        EU_BCAST[18] = null;
        EU_BCAST[19] = null;
        EU_BCAST[20] = null;
        EU_BCAST[21] = new Frequency(FrequencyType.EU_BCAST, 21, 474000000);
        EU_BCAST[22] = new Frequency(FrequencyType.EU_BCAST, 22, 482000000);
        EU_BCAST[23] = new Frequency(FrequencyType.EU_BCAST, 23, 490000000);
        EU_BCAST[24] = new Frequency(FrequencyType.EU_BCAST, 24, 498000000);
        EU_BCAST[25] = new Frequency(FrequencyType.EU_BCAST, 25, 506000000);
        EU_BCAST[26] = new Frequency(FrequencyType.EU_BCAST, 26, 514000000);
        EU_BCAST[27] = new Frequency(FrequencyType.EU_BCAST, 27, 522000000);
        EU_BCAST[28] = new Frequency(FrequencyType.EU_BCAST, 28, 530000000);
        EU_BCAST[29] = new Frequency(FrequencyType.EU_BCAST, 29, 538000000);
        EU_BCAST[30] = new Frequency(FrequencyType.EU_BCAST, 30, 546000000);
        EU_BCAST[31] = new Frequency(FrequencyType.EU_BCAST, 31, 554000000);
        EU_BCAST[32] = new Frequency(FrequencyType.EU_BCAST, 32, 562000000);
        EU_BCAST[33] = new Frequency(FrequencyType.EU_BCAST, 33, 570000000);
        EU_BCAST[34] = new Frequency(FrequencyType.EU_BCAST, 34, 578000000);
        EU_BCAST[35] = new Frequency(FrequencyType.EU_BCAST, 35, 586000000);
        EU_BCAST[36] = new Frequency(FrequencyType.EU_BCAST, 36, 594000000);
        EU_BCAST[37] = new Frequency(FrequencyType.EU_BCAST, 37, 602000000);
        EU_BCAST[38] = new Frequency(FrequencyType.EU_BCAST, 38, 610000000);
        EU_BCAST[39] = new Frequency(FrequencyType.EU_BCAST, 39, 618000000);
        EU_BCAST[40] = new Frequency(FrequencyType.EU_BCAST, 40, 626000000);
        EU_BCAST[41] = new Frequency(FrequencyType.EU_BCAST, 41, 634000000);
        EU_BCAST[42] = new Frequency(FrequencyType.EU_BCAST, 42, 642000000);
        EU_BCAST[43] = new Frequency(FrequencyType.EU_BCAST, 43, 650000000);
        EU_BCAST[44] = new Frequency(FrequencyType.EU_BCAST, 44, 658000000);
        EU_BCAST[45] = new Frequency(FrequencyType.EU_BCAST, 45, 666000000);
        EU_BCAST[46] = new Frequency(FrequencyType.EU_BCAST, 46, 674000000);
        EU_BCAST[47] = new Frequency(FrequencyType.EU_BCAST, 47, 682000000);
        EU_BCAST[48] = new Frequency(FrequencyType.EU_BCAST, 48, 690000000);
        EU_BCAST[49] = new Frequency(FrequencyType.EU_BCAST, 49, 698000000);
        EU_BCAST[50] = new Frequency(FrequencyType.EU_BCAST, 50, 706000000);
        EU_BCAST[51] = new Frequency(FrequencyType.EU_BCAST, 51, 714000000);
        EU_BCAST[52] = new Frequency(FrequencyType.EU_BCAST, 52, 722000000);
        EU_BCAST[53] = new Frequency(FrequencyType.EU_BCAST, 53, 730000000);
        EU_BCAST[54] = new Frequency(FrequencyType.EU_BCAST, 54, 738000000);
        EU_BCAST[55] = new Frequency(FrequencyType.EU_BCAST, 55, 746000000);
        EU_BCAST[56] = new Frequency(FrequencyType.EU_BCAST, 56, 754000000);
        EU_BCAST[57] = new Frequency(FrequencyType.EU_BCAST, 57, 762000000);
        EU_BCAST[58] = new Frequency(FrequencyType.EU_BCAST, 58, 770000000);
        EU_BCAST[59] = new Frequency(FrequencyType.EU_BCAST, 59, 778000000);
        EU_BCAST[60] = new Frequency(FrequencyType.EU_BCAST, 60, 786000000);
        EU_BCAST[61] = new Frequency(FrequencyType.EU_BCAST, 61, 794000000);
        EU_BCAST[62] = new Frequency(FrequencyType.EU_BCAST, 62, 802000000);
        EU_BCAST[63] = new Frequency(FrequencyType.EU_BCAST, 63, 810000000);
        EU_BCAST[64] = new Frequency(FrequencyType.EU_BCAST, 64, 818000000);
        EU_BCAST[65] = new Frequency(FrequencyType.EU_BCAST, 65, 826000000);
        EU_BCAST[66] = new Frequency(FrequencyType.EU_BCAST, 66, 834000000);
        EU_BCAST[67] = new Frequency(FrequencyType.EU_BCAST, 67, 842000000);
        EU_BCAST[68] = new Frequency(FrequencyType.EU_BCAST, 68, 850000000);
        EU_BCAST[69] = new Frequency(FrequencyType.EU_BCAST, 69, 858000000);


        EU_CABLE = new Frequency[159];

        // These could be wrong.
        EU_CABLE[0] = null;
        EU_CABLE[1] = null;
        EU_CABLE[2] = new Frequency(FrequencyType.EU_CABLE, 2, 57000000);
        EU_CABLE[3] = new Frequency(FrequencyType.EU_CABLE, 3, 63000000);
        EU_CABLE[4] = new Frequency(FrequencyType.EU_CABLE, 4, 69000000);
        EU_CABLE[5] = new Frequency(FrequencyType.EU_CABLE, 5, 79000000);
        EU_CABLE[6] = new Frequency(FrequencyType.EU_CABLE, 6, 85000000);
        EU_CABLE[7] = new Frequency(FrequencyType.EU_CABLE, 7, 177000000);
        EU_CABLE[8] = new Frequency(FrequencyType.EU_CABLE, 8, 183000000);
        EU_CABLE[9] = new Frequency(FrequencyType.EU_CABLE, 9, 189000000);
        EU_CABLE[10] = new Frequency(FrequencyType.EU_CABLE, 10, 195000000);
        EU_CABLE[11] = new Frequency(FrequencyType.EU_CABLE, 11, 201000000);
        EU_CABLE[12] = new Frequency(FrequencyType.EU_CABLE, 12, 207000000);
        EU_CABLE[13] = new Frequency(FrequencyType.EU_CABLE, 13, 213000000);
        EU_CABLE[14] = new Frequency(FrequencyType.EU_CABLE, 14, 123000000);
        EU_CABLE[15] = new Frequency(FrequencyType.EU_CABLE, 15, 129000000);
        EU_CABLE[16] = new Frequency(FrequencyType.EU_CABLE, 16, 135000000);
        EU_CABLE[17] = new Frequency(FrequencyType.EU_CABLE, 17, 141000000);
        EU_CABLE[18] = new Frequency(FrequencyType.EU_CABLE, 18, 147000000);
        EU_CABLE[19] = new Frequency(FrequencyType.EU_CABLE, 19, 153000000);
        EU_CABLE[20] = new Frequency(FrequencyType.EU_CABLE, 20, 159000000);
        EU_CABLE[21] = new Frequency(FrequencyType.EU_CABLE, 21, 165000000);
        EU_CABLE[22] = new Frequency(FrequencyType.EU_CABLE, 22, 171000000);
        EU_CABLE[23] = new Frequency(FrequencyType.EU_CABLE, 23, 219000000);
        EU_CABLE[24] = new Frequency(FrequencyType.EU_CABLE, 24, 225000000);
        EU_CABLE[25] = new Frequency(FrequencyType.EU_CABLE, 25, 231000000);
        EU_CABLE[26] = new Frequency(FrequencyType.EU_CABLE, 26, 237000000);
        EU_CABLE[27] = new Frequency(FrequencyType.EU_CABLE, 27, 243000000);
        EU_CABLE[28] = new Frequency(FrequencyType.EU_CABLE, 28, 249000000);
        EU_CABLE[29] = new Frequency(FrequencyType.EU_CABLE, 29, 255000000);
        EU_CABLE[30] = new Frequency(FrequencyType.EU_CABLE, 30, 261000000);
        EU_CABLE[31] = new Frequency(FrequencyType.EU_CABLE, 31, 267000000);
        EU_CABLE[32] = new Frequency(FrequencyType.EU_CABLE, 32, 273000000);
        EU_CABLE[33] = new Frequency(FrequencyType.EU_CABLE, 33, 279000000);
        EU_CABLE[34] = new Frequency(FrequencyType.EU_CABLE, 34, 285000000);
        EU_CABLE[35] = new Frequency(FrequencyType.EU_CABLE, 35, 291000000);
        EU_CABLE[36] = new Frequency(FrequencyType.EU_CABLE, 36, 297000000);
        EU_CABLE[37] = new Frequency(FrequencyType.EU_CABLE, 37, 303000000);
        EU_CABLE[38] = new Frequency(FrequencyType.EU_CABLE, 38, 309000000);
        EU_CABLE[39] = new Frequency(FrequencyType.EU_CABLE, 39, 315000000);
        EU_CABLE[40] = new Frequency(FrequencyType.EU_CABLE, 40, 321000000);
        EU_CABLE[41] = new Frequency(FrequencyType.EU_CABLE, 41, 327000000);
        EU_CABLE[42] = new Frequency(FrequencyType.EU_CABLE, 42, 333000000);
        EU_CABLE[43] = new Frequency(FrequencyType.EU_CABLE, 43, 339000000);
        EU_CABLE[44] = new Frequency(FrequencyType.EU_CABLE, 44, 345000000);
        EU_CABLE[45] = new Frequency(FrequencyType.EU_CABLE, 45, 351000000);
        EU_CABLE[46] = new Frequency(FrequencyType.EU_CABLE, 46, 357000000);
        EU_CABLE[47] = new Frequency(FrequencyType.EU_CABLE, 47, 363000000);
        EU_CABLE[48] = new Frequency(FrequencyType.EU_CABLE, 48, 369000000);
        EU_CABLE[49] = new Frequency(FrequencyType.EU_CABLE, 49, 375000000);
        EU_CABLE[50] = new Frequency(FrequencyType.EU_CABLE, 50, 381000000);
        EU_CABLE[51] = new Frequency(FrequencyType.EU_CABLE, 51, 387000000);
        EU_CABLE[52] = new Frequency(FrequencyType.EU_CABLE, 52, 393000000);
        EU_CABLE[53] = new Frequency(FrequencyType.EU_CABLE, 53, 399000000);
        EU_CABLE[54] = new Frequency(FrequencyType.EU_CABLE, 54, 405000000);
        EU_CABLE[55] = new Frequency(FrequencyType.EU_CABLE, 55, 411000000);
        EU_CABLE[56] = new Frequency(FrequencyType.EU_CABLE, 56, 417000000);
        EU_CABLE[57] = new Frequency(FrequencyType.EU_CABLE, 57, 423000000);
        EU_CABLE[58] = new Frequency(FrequencyType.EU_CABLE, 58, 429000000);
        EU_CABLE[59] = new Frequency(FrequencyType.EU_CABLE, 59, 435000000);
        EU_CABLE[60] = new Frequency(FrequencyType.EU_CABLE, 60, 441000000);
        EU_CABLE[61] = new Frequency(FrequencyType.EU_CABLE, 61, 447000000);
        EU_CABLE[62] = new Frequency(FrequencyType.EU_CABLE, 62, 453000000);
        EU_CABLE[63] = new Frequency(FrequencyType.EU_CABLE, 63, 459000000);
        EU_CABLE[64] = new Frequency(FrequencyType.EU_CABLE, 64, 465000000);
        EU_CABLE[65] = new Frequency(FrequencyType.EU_CABLE, 65, 471000000);
        EU_CABLE[66] = new Frequency(FrequencyType.EU_CABLE, 66, 477000000);
        EU_CABLE[67] = new Frequency(FrequencyType.EU_CABLE, 67, 483000000);
        EU_CABLE[68] = new Frequency(FrequencyType.EU_CABLE, 68, 489000000);
        EU_CABLE[69] = new Frequency(FrequencyType.EU_CABLE, 69, 495000000);
        EU_CABLE[70] = new Frequency(FrequencyType.EU_CABLE, 70, 501000000);
        EU_CABLE[71] = new Frequency(FrequencyType.EU_CABLE, 71, 507000000);
        EU_CABLE[72] = new Frequency(FrequencyType.EU_CABLE, 72, 513000000);
        EU_CABLE[73] = new Frequency(FrequencyType.EU_CABLE, 73, 519000000);
        EU_CABLE[74] = new Frequency(FrequencyType.EU_CABLE, 74, 525000000);
        EU_CABLE[75] = new Frequency(FrequencyType.EU_CABLE, 75, 531000000);
        EU_CABLE[76] = new Frequency(FrequencyType.EU_CABLE, 76, 537000000);
        EU_CABLE[77] = new Frequency(FrequencyType.EU_CABLE, 77, 543000000);
        EU_CABLE[78] = new Frequency(FrequencyType.EU_CABLE, 78, 549000000);
        EU_CABLE[79] = new Frequency(FrequencyType.EU_CABLE, 79, 555000000);
        EU_CABLE[80] = new Frequency(FrequencyType.EU_CABLE, 80, 561000000);
        EU_CABLE[81] = new Frequency(FrequencyType.EU_CABLE, 81, 567000000);
        EU_CABLE[82] = new Frequency(FrequencyType.EU_CABLE, 82, 573000000);
        EU_CABLE[83] = new Frequency(FrequencyType.EU_CABLE, 83, 579000000);
        EU_CABLE[84] = new Frequency(FrequencyType.EU_CABLE, 84, 585000000);
        EU_CABLE[85] = new Frequency(FrequencyType.EU_CABLE, 85, 591000000);
        EU_CABLE[86] = new Frequency(FrequencyType.EU_CABLE, 86, 597000000);
        EU_CABLE[87] = new Frequency(FrequencyType.EU_CABLE, 87, 603000000);
        EU_CABLE[88] = new Frequency(FrequencyType.EU_CABLE, 88, 609000000);
        EU_CABLE[89] = new Frequency(FrequencyType.EU_CABLE, 89, 615000000);
        EU_CABLE[90] = new Frequency(FrequencyType.EU_CABLE, 90, 621000000);
        EU_CABLE[91] = new Frequency(FrequencyType.EU_CABLE, 91, 627000000);
        EU_CABLE[92] = new Frequency(FrequencyType.EU_CABLE, 92, 633000000);
        EU_CABLE[93] = new Frequency(FrequencyType.EU_CABLE, 93, 639000000);
        EU_CABLE[94] = new Frequency(FrequencyType.EU_CABLE, 94, 645000000);
        EU_CABLE[95] = new Frequency(FrequencyType.EU_CABLE, 95, 93000000);
        EU_CABLE[96] = new Frequency(FrequencyType.EU_CABLE, 96, 99000000);
        EU_CABLE[97] = new Frequency(FrequencyType.EU_CABLE, 97, 105000000);
        EU_CABLE[98] = new Frequency(FrequencyType.EU_CABLE, 98, 111000000);
        EU_CABLE[99] = new Frequency(FrequencyType.EU_CABLE, 99, 117000000);
        EU_CABLE[100] = new Frequency(FrequencyType.EU_CABLE, 100, 651000000);
        EU_CABLE[101] = new Frequency(FrequencyType.EU_CABLE, 101, 657000000);
        EU_CABLE[102] = new Frequency(FrequencyType.EU_CABLE, 102, 663000000);
        EU_CABLE[103] = new Frequency(FrequencyType.EU_CABLE, 103, 669000000);
        EU_CABLE[104] = new Frequency(FrequencyType.EU_CABLE, 104, 675000000);
        EU_CABLE[105] = new Frequency(FrequencyType.EU_CABLE, 105, 681000000);
        EU_CABLE[106] = new Frequency(FrequencyType.EU_CABLE, 106, 687000000);
        EU_CABLE[107] = new Frequency(FrequencyType.EU_CABLE, 107, 693000000);
        EU_CABLE[108] = new Frequency(FrequencyType.EU_CABLE, 108, 699000000);
        EU_CABLE[109] = new Frequency(FrequencyType.EU_CABLE, 109, 705000000);
        EU_CABLE[110] = new Frequency(FrequencyType.EU_CABLE, 110, 711000000);
        EU_CABLE[111] = new Frequency(FrequencyType.EU_CABLE, 111, 717000000);
        EU_CABLE[112] = new Frequency(FrequencyType.EU_CABLE, 112, 723000000);
        EU_CABLE[113] = new Frequency(FrequencyType.EU_CABLE, 113, 729000000);
        EU_CABLE[114] = new Frequency(FrequencyType.EU_CABLE, 114, 735000000);
        EU_CABLE[115] = new Frequency(FrequencyType.EU_CABLE, 115, 741000000);
        EU_CABLE[116] = new Frequency(FrequencyType.EU_CABLE, 116, 747000000);
        EU_CABLE[117] = new Frequency(FrequencyType.EU_CABLE, 117, 753000000);
        EU_CABLE[118] = new Frequency(FrequencyType.EU_CABLE, 118, 759000000);
        EU_CABLE[119] = new Frequency(FrequencyType.EU_CABLE, 119, 765000000);
        EU_CABLE[120] = new Frequency(FrequencyType.EU_CABLE, 120, 771000000);
        EU_CABLE[121] = new Frequency(FrequencyType.EU_CABLE, 121, 777000000);
        EU_CABLE[122] = new Frequency(FrequencyType.EU_CABLE, 122, 783000000);
        EU_CABLE[123] = new Frequency(FrequencyType.EU_CABLE, 123, 789000000);
        EU_CABLE[124] = new Frequency(FrequencyType.EU_CABLE, 124, 795000000);
        EU_CABLE[125] = new Frequency(FrequencyType.EU_CABLE, 125, 801000000);
        EU_CABLE[126] = new Frequency(FrequencyType.EU_CABLE, 126, 807000000);
        EU_CABLE[127] = new Frequency(FrequencyType.EU_CABLE, 127, 813000000);
        EU_CABLE[128] = new Frequency(FrequencyType.EU_CABLE, 128, 819000000);
        EU_CABLE[129] = new Frequency(FrequencyType.EU_CABLE, 129, 825000000);
        EU_CABLE[130] = new Frequency(FrequencyType.EU_CABLE, 130, 831000000);
        EU_CABLE[131] = new Frequency(FrequencyType.EU_CABLE, 131, 837000000);
        EU_CABLE[132] = new Frequency(FrequencyType.EU_CABLE, 132, 843000000);
        EU_CABLE[133] = new Frequency(FrequencyType.EU_CABLE, 133, 849000000);
        EU_CABLE[134] = new Frequency(FrequencyType.EU_CABLE, 134, 855000000);
        EU_CABLE[135] = new Frequency(FrequencyType.EU_CABLE, 135, 861000000);
        EU_CABLE[136] = new Frequency(FrequencyType.EU_CABLE, 136, 867000000);
        EU_CABLE[137] = new Frequency(FrequencyType.EU_CABLE, 137, 873000000);
        EU_CABLE[138] = new Frequency(FrequencyType.EU_CABLE, 138, 879000000);
        EU_CABLE[139] = new Frequency(FrequencyType.EU_CABLE, 139, 885000000);
        EU_CABLE[140] = new Frequency(FrequencyType.EU_CABLE, 140, 891000000);
        EU_CABLE[141] = new Frequency(FrequencyType.EU_CABLE, 141, 897000000);
        EU_CABLE[142] = new Frequency(FrequencyType.EU_CABLE, 142, 903000000);
        EU_CABLE[143] = new Frequency(FrequencyType.EU_CABLE, 143, 909000000);
        EU_CABLE[144] = new Frequency(FrequencyType.EU_CABLE, 144, 915000000);
        EU_CABLE[145] = new Frequency(FrequencyType.EU_CABLE, 145, 921000000);
        EU_CABLE[146] = new Frequency(FrequencyType.EU_CABLE, 146, 927000000);
        EU_CABLE[147] = new Frequency(FrequencyType.EU_CABLE, 147, 933000000);
        EU_CABLE[148] = new Frequency(FrequencyType.EU_CABLE, 148, 939000000);
        EU_CABLE[149] = new Frequency(FrequencyType.EU_CABLE, 149, 945000000);
        EU_CABLE[150] = new Frequency(FrequencyType.EU_CABLE, 150, 951000000);
        EU_CABLE[151] = new Frequency(FrequencyType.EU_CABLE, 151, 957000000);
        EU_CABLE[152] = new Frequency(FrequencyType.EU_CABLE, 152, 963000000);
        EU_CABLE[153] = new Frequency(FrequencyType.EU_CABLE, 153, 969000000);
        EU_CABLE[154] = new Frequency(FrequencyType.EU_CABLE, 154, 975000000);
        EU_CABLE[155] = new Frequency(FrequencyType.EU_CABLE, 155, 981000000);
        EU_CABLE[156] = new Frequency(FrequencyType.EU_CABLE, 156, 987000000);
        EU_CABLE[157] = new Frequency(FrequencyType.EU_CABLE, 157, 993000000);
        EU_CABLE[158] = new Frequency(FrequencyType.EU_CABLE, 158, 999000000);

    }
}
