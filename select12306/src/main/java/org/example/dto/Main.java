package org.example.dto;

import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HttpGlobalConfig.setTimeout(1000 * 60 * 3);
        System.out.println("Hello world!");

        List<String[]> addressList = new ArrayList<>();
        addressList.add(new String[]{"西安北", "EAY"});
        addressList.add(new String[]{"华山北", "HDY"});
        addressList.add(new String[]{"洛阳龙门", "LLF"});
        addressList.add(new String[]{"郑州东", "ZAF"});
        addressList.add(new String[]{"商丘", "SQF"});
        addressList.add(new String[]{"亳州南", "BNU"});
        addressList.add(new String[]{"古城东", "GUU"});
        addressList.add(new String[]{"太和东", "TDU"});
        addressList.add(new String[]{"阜阳西", "FXU"});
        addressList.add(new String[]{"颍上北", "YBU"});
        addressList.add(new String[]{"寿县", "SOU"});
        String[] ENH = {"合肥南", "ENH"};
        addressList.add(ENH);
        addressList.add(new String[]{"巢湖东", "GUH"});
        addressList.add(new String[]{"铜陵北", "KXH"});
        addressList.add(new String[]{"旌德", "NSH"});
        addressList.add(new String[]{"黄山北", "NYH"});
        addressList.add(new String[]{"婺源", "WYG"});
        addressList.add(new String[]{"武夷山北", "WBS"});
        addressList.add(new String[]{"南平市", "NOS"});
        addressList.add(new String[]{"延平", "YPS"});
        addressList.add(new String[]{"福州", "FZS"});
        addressList.add(new String[]{"福州南", "FYS"});
        addressList.add(new String[]{"平潭", "PIS"});

        String url = "https://kyfw.12306.cn/otn/leftTicket/queryG";
        List<URLreq> urlList = new ArrayList<>();
        int indexHFN = addressList.indexOf(ENH);
        for (int indexOfFrom = indexHFN; indexOfFrom >= 0; indexOfFrom--) {
            for (int indexOfTo = addressList.size() - 1; indexOfTo >= indexHFN; indexOfTo--) {
                String[] from = addressList.get(indexOfFrom);
                String[] to = addressList.get(indexOfTo);
                if (indexOfTo > 11) {
                    if (indexOfFrom <= 11 && indexOfFrom >= 5) {
                        String url1 = url;
                        String[] parameter = new String[4];
                        parameter[0] = "leftTicketDTO.train_date=2024-04-30";
                        parameter[1] = "leftTicketDTO.from_station=" + from[1];
                        parameter[2] = "leftTicketDTO.to_station=" + to[1];
                        parameter[3] = "purpose_codes=ADULT";
                        url1 = url1 + "?" + parameter[0] + "&" + parameter[1] + "&" + parameter[2] + "&" + parameter[3];
                        URLreq urlreq = new URLreq();
                        urlreq.url = url1;
                        urlreq.len = indexOfTo - indexOfFrom;
                        urlreq.lenHF = indexHFN - indexOfFrom;
                        urlreq.des = indexOfFrom + from[0] + "--->" + indexOfTo + to[0] + ">>>>>";
                        urlList.add(urlreq);
                    }
                }
            }
        }
        Collections.sort(urlList);
        System.out.print(urlList);

        Integer[] aaaa = new Integer[urlList.size()];
        Arrays.fill(aaaa, 0);
        Random random = new Random();
        while (true) {
            int j = random.nextInt(1000) % urlList.size();
            int k = random.nextInt(j + 1);
            aaaa[k] = aaaa[k] + 1;
            URLreq urlitem = urlList.get(k);
            List<String> resultList = getresult(urlitem.url);

            if (resultList != null) {
                System.out.print(urlitem.des + "共计班车" + resultList.size() + "辆");
                System.out.println("===长度" + urlitem.len + "站===提前合肥南" + urlitem.lenHF + "站上车");
                for (String result : resultList) {
                    String[] split = result.split("\\|");
                    String flage = null;
                    if (result.contains("G859")) {
                        flage = "G859";
                    }
                    if (result.contains("G862")) {
                        flage = "G862";
                    }
                    if (flage != null) {
                        System.out.println(result.substring(result.indexOf(flage)));
                        System.out.println(split[3] + ">>>" + split[30] + ">>>" + split[31] + ">>>" + split[32]);
                        if (urlitem.len < 3) {
                            if (!split[31].equals("无") || !split[32].equals("无")) {
                                play();
                                System.out.println("注意！！！！" + urlitem.des + ">>>一等座>>" + split[31] + ">>>商务舱>>" + split[32]);
                            }
                        }
                        if (!split[30].equals("无")) {
                            play();
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                            System.out.println("抢！！！！" + urlitem.des + ">>>剩下>>" + split[30]);
                        }
                    }
                }
            }
            Thread.sleep(2000 + random.nextInt(2000));
        }
    }

    private static void play() {
        File wav = new File(
                "C:\\Users\\zachary\\IdeaProjects\\select12306\\src\\main\\java\\org\\example\\dto" +
                        "\\WindowsProximityConnection.wav");

        try {
            URI url1 = wav.toURI();
            InputStream inputStream = url1.toURL().openStream();
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//            }
//        }

    private static List<String> getresult(String url) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp," + "image/apng,*/*;"
                        + "q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("Accept-Encoding", "gzip, deflate, br, zstd");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        headers.put(
                "Cookie",
                "");
        headers.put("Host", "kyfw.12306.cn");
        headers.put("Pragma", "no-cache");
        headers.put(
                "Sec-Ch-Ua",
                "\"Microsoft Edge\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";" + "v=\"123" + "\"");
        headers.put("Sec-Ch-Ua-Mobile", "?0");
        headers.put("Sec-Ch-Ua-Platform", "\"Windows\"");
        headers.put("Sec-Fetch-Dest", "document");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Sec-Fetch-Site", "none");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " + "Gecko) " +
                        "Chrome/123" + ".0.0.0 Safari/537.36 Edg/123.0.0.0");


        HttpRequest httpRequest = HttpRequest.get(url).addHeaders(headers);
//                            System.out.println(httpRequest);

        try (HttpResponse httpResponse = httpRequest.execute()) {
            if (httpResponse.isOk()) {
                JSONObject jsonObject = JSONUtil.parseObj(httpResponse.body());
                if (200 == jsonObject.get("httpstatus", Integer.class)) {
                    return jsonObject.get("data", JSONObject.class).getBeanList("result", String.class);
                }
            }
            System.out.println(httpRequest);
            System.out.println(httpResponse);
        }
        return null;
    }
}