package ru.otus.lottery;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tully.
 */
public class Main {
    private static final int MAX_WINNERS_COUNT = 5;

    public static void main(String[] args) throws IOException {
        String pathToFile;
        if (args.length >= 1) {
            pathToFile = args[0];
        } else {
            pathToFile = "emails.csv";
        }

        System.out.println("Reading emails from: " + pathToFile);

        CSVReader reader = new CSVReader(new FileReader(pathToFile));
        List<String> emails = reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());

        System.out.println("Emails count: " + emails.size());

        String seedString = "Алексей Зверев (10:28): для лотереи нужно присутствовать в онлайне?\n" +
                "Dmitry Voloshin (10:28): К Скольково никакого отношения не имеет )\n" +
                "Oxana Kotlova (10:29): как я понимаю, Алеексей, желательно. \n" +
                "Dmitry Voloshin (10:29): *Сколково\n" +
                "Андрей Самойлов (10:29): в хроме вообще не загружается\n" +
                "Dmitry Voloshin (10:30): С хромом беда, направили баг реквест\n" +
                "Алексей Зверев (10:30): Дмитрий, нужно ли присутствовать в онлайне для участия в лотерее?\n" +
                "Dmitry Voloshin (10:31): Алексей, мы среди всех зарегов разыграем\n" +
                "Андрей Стефанович (10:31): А если знаний нет и тест не прошёл? \n" +
                "Dmitry Voloshin (10:32): Андрей, тогда лучше не стоит начинать наш курс. Попробуйте начать на более простых\n" +
                "Andrew S (10:33): для Андрей Стефанович. Проийти курс на Степик. Он простой и бесплатный.\n" +
                "Dmitriy Kotov (10:34): Насколько глубоко будут разбираться эти темы по ходу курса? На том же stepic многие из этих тем поднимались, но там им уделялось крайне малое время\n" +
                "Dmitriy Kotov (10:35): Те же паттерны проектирования определенно не на 2 часовую лекцию\n" +
                "Вера Бедрицкая (10:37): в таком случае, я считаю, очень странное название вы выбрали (почему отус). что это?попытка привлечь внимание за счет уже существующего бренда?\n" +
                "Павел Турлачев (10:37): Время на выполнение заданий ограничено?\n" +
                "Алексей Зверев (10:38): до какого числа нужно определиться с участием в курсе?\n" +
                "Христиан Чернецов (10:39): Какой то экзамен по теории будет?\n" +
                "Христиан Чернецов (10:39): в конце\n" +
                "Андрей Самойлов (10:41): время проведения вебинара?\n" +
                "Dmitry Voloshin (10:42): Вера, с названием получилось так: мы думали про сову, как символ знаний. Нашли редкий вид - отус, посмотрите в вики. А потом, когда купили домен, сделали брендинг, и почти запустились узнали про Сколково )\n" +
                "Dmitry Voloshin (10:42): Павел, время ограниченно\n" +
                "Dmitry Voloshin (10:42): Алексей, до 31 марта. Есть нюанс - как только мы получим 40 оплат, то закроем набор\n" +
                "Ленмар Абдурайимов (10:43): а можно ли лекции не утром, а вечером?\n" +
                "Andrew S (10:44): для Dmitry Voloshin Сколько уже оплат прошло?\n" +
                "Dmitry Voloshin (10:45): Andrew S, 16\n" +
                "Ленмар Абдурайимов (10:45): те кто за месяц только оплатил - считаются?\n" +
                "Dmitry Voloshin (10:46): Ленмар, конечно. \n" +
                "Oxana Kotlova (10:46): а если я оплаччу сразу весь курс - у меня шаносв дойти до конца  больше?\n" +
                "Ленмар Абдурайимов (10:47): обязательно ли присутствовать утром на занятиях? Или можно потом запись посмотреть? Спасибо!\n" +
                "Dmitry Voloshin (10:47): Ленмар, все занятия будут записаны\n" +
                "Artem Bardakov (10:48): Прошу прощения, немного опоздал, а можно увидеть список счастливчиков на бесплатное обучение?\n" +
                "Dmitry Voloshin (10:48): Артем, мы еще не разыграли\n" +
                "Oxana Kotlova (10:48): думаю, что купить курс целиком и выгоднее и надежнее\n" +
                "Сергей Иванов (10:49): звук падает\n" +
                "Сергей Иванов (10:49): иногда\n" +
                "Andrew S (10:49): не появилось ничего. Проговорите пожалуйста все технические средства голосом.\n" +
                "Dmitry Voloshin (10:50): Andrew S, проговаривает )\n" +
                "Artem Bardakov (10:51): Виталий, можно узнать, почему предочтение было отдано Spring & Hibernate, вместо JEE (EJB,JPA, JMS и т.д";

        List<String> winners = new LotteryMachine(emails, MAX_WINNERS_COUNT).setSeed(seedString).draw();

        System.out.println("Winners:");
        winners.forEach(System.out::println);
        // Result:
        // Reading emails from: emails.csv
        // Emails count: 413
        // Draw for the seed: 1770509124
        // Ball: 90
        // Ball: 7
        // Ball: 335
        // Ball: 137
        // Ball: 191
        // Winners:
        // jfeoks
        // chengaro
        // karpov_alexey
        // drizer84
        // dinoradrau
    }
}
