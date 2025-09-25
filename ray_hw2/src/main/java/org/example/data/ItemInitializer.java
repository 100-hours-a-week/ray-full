package org.example.data;

import org.example.data.AssetInfo;

import java.util.List;

public class ItemInitializer {
    public static List<AssetInfo> building(){
        return List.of(
                new AssetInfo("우리집 뒷마당 컨테이너", 10, 1),
                new AssetInfo("우리집 앞마당 빌딩", 100, 5),
                new AssetInfo("63빌딩", 500, 10),
                new AssetInfo("롯데월드타워", 1000, 20),
                new AssetInfo("구글 본사 건물", 5000, 50)
        );
    }
    public static List<AssetInfo> stock(){
        return List.of(
                new AssetInfo("빙그래", 20, 6),
                new AssetInfo("하이트진로", 200, 30),
                new AssetInfo("삼성전자", 1000, 60),
                new AssetInfo("테슬라", 2000, 120),
                new AssetInfo("버크셔 해서웨이 A주", 10000, 500)
        );
    }

    public static List<EmployInfo> employ(){
        return List.of(
                new EmployInfo("김철수", 50, 50,"근면성실하지만 센스는 없는 편" ),
                new EmployInfo("최강록", 200, 150,"요리를 잘 조릴 것만 같은 편"),
                new EmployInfo("유희왕", 5000, 2000,"마이웨이이고 자신만의 세계에 갇혀사는 편"),
                new EmployInfo("이타치", 300, 200,"고용하면 중간에 딴길로 샐 것 만 같은 편.."),
                new EmployInfo("에드워드 알릭", 1000, 500,"진리를 깨우친 것 같은 편!")
        );
    }

}
