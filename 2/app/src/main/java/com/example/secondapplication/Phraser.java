package com.example.secondapplication;

public class Phraser {
    static String[] wordListOne = {"круглосуточный", "трех-звенный",
            "30-футовьй", "взаимный", "обоюдный выигрыш", "фронтэнд",
            "на основе веб-технологий", "проникащий", "умный", "динамичный"};

    static String[] wordListTwo = {"уполномоченный", "трудный",
            "чистый продукт", "ориентированный", "центральный",
            "распределенный", "кластеризованный", "фирменный",
            "нестандартный ум", "позиционированный", "сетевой",
            "сфокусированный", "использованный с выгодой", "выровненный",
            "целесообразный", "общий", "совместный", "ускоренный"};

    static String[] wordListThree = {"процесс", "пункт разгрузки",
            "выход из положения", "тип структуры", "талант", "подход",
            "уровень завоеванного внимания", "портал", "период времени",
            "обзор", "образец", "пункт следования"};

    public static String generate() {
        // Генерируем три случайных числа
        int rl = (int) (Math.random() * wordListOne.length) ;
        int r2 = (int) (Math.random() * wordListTwo.length) ;
        int r3 = (int) (Math.random() * wordListThree.length);
        // Теперь строим фразу
        String phrase = wordListOne[rl] + " " + wordListTwo[r2] + " " + wordListThree[r3];
        
        return phrase;
    }
}
