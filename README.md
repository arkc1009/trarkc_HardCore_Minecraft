# trarkc_HardCore_Minecraft
마인크래프트의 야생을 조금 더 어렵게



## 1. 제한 

### 1-1. 파괴 제한
블럭들을 종류에 따라 파괴 불가로 만든다.
> 맨손으로는 나무, 돌 종류의 블럭을 파괴하지 못하도록 함.
> 각 블럭에 맞는 도구가 존재하여야 블럭 파괴 가능.

### 1-2. 식사 제한
> 날것의 음식을 먹으면 식중독 상태에 걸림.


## 2. 효과

### 2-1. 식중독
날것의 음식을 먹었을 때 걸리는 효과
> 멀미, 독, 배고픔 등의 디버프가 걸림.
> /Toe 커맨드를 통해 해결 가능.
```java
public void RawEffect(Player p) {
        List<PotionEffect> pelist = new ArrayList<>();
        pelist.add(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1));
        pelist.add(new PotionEffect(PotionEffectType.POISON, 600, 0));
        pelist.add(new PotionEffect(PotionEffectType.HUNGER, 2000, 0));

        p.sendMessage("§c날것의 음식을 먹어서 식중독 상태에 걸렸습니다!\n§4/Toe 를 이용하여 위 문제를 해결하세요!");

        hashMap.put(p.getName(), true);

        for(PotionEffect pe : pelist) {
            p.addPotionEffect(pe);
        }
    }
```
> 위 처럼 List에 포션이펙트들을 넣고 ForEach를 통해 버프를 주었음.
> 또, 커맨드를 입력한 플레이어가 식중독 상태인지를 판별하기 위해 해쉬맵을 사용함.

## 3. 커맨드

### 3-1. /Toe
식중독 디버프를 해결해주는 커맨드
> 식중독 상태가 아니면 사용할 수 없음.
> 커맨드 그대로 **토**를 의미 하기 때문에 토를 받아낼 빈 양동이가 필요.

## 4. 기능

### 4-1. 엘리트몬스터/보스
강한 필드몬스터
> 필드에 스폰되는 몬스터들이 가끔 강한 엘리트몬스터로 스폰.
> 당연히 일반 몹보다 강력하고 잡았을때에 보상이 존재함
~~엘리트보스는 아직~~

### 4-2. 헤드샷
추가데미지
> 활과 화살로 엔티티의 머리를 맞추면 1.5배의 데미지.


