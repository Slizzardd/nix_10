insert into drivers
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Christian', 'Bauer', 100,
        'НЕАДЕВКАТ!!! Заказы лучше не давать', 'https://www.psi.ch/sites/default/files/styles/psi_content_half_sm/public/2020-02/bauer_christian_0005.jpg?itok=M83eoQ7p');

insert into drivers
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Gavin', 'King', 100,
        'Хороший человек, ухоженная машина', 'https://avatars.githubusercontent.com/u/579974?v=4');

insert into drivers
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Gary', 'Gregory', 100,
        'Просто нет слов', 'https://avatars.githubusercontent.com/u/1187639?v=4');

insert into drivers
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Bruce', 'Eckel', 100,
        'БОЖЕНЬКА', 'https://knigogid.ru/storage/authors/04e/276/1c8/e17719e1382e889b367db272c918413b.jpg');

insert into drivers
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Brian', 'Goetz', 100,
        'Просто хороший человек', 'https://qconlondon.com/london-2017/sites/default/files/styles/img-single-track/public/10094934084_2006994711977a.jpg?itok=x4kf5DB5');

insert into drivers
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Tim', 'Peierls', 100,
        'Я уже не знаю что писать', 'https://avatars.githubusercontent.com/u/237258?v=4');

insert into drivers
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Joshua', 'Bloch', 100,
        'Топчик', 'https://cdn.javarush.ru/images/article/2a6d3ec7-4b50-4201-b1ef-fa9b65642430/original.jpeg');

insert into drivers
values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Joseph', 'Bowbeer', 100,
        'ААААААААААААААААААААААААА', 'https://www.hbs.edu/news/PublishingImages/Joe%20Bower%20Headshot_small.jpg');

insert into drivers
values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'David', 'Holmes', 100,
        'Рецепт оладушков: Мука пшеничная - 250 г
                            Молоко - 200 мл
                            Масло растительное - 3 ст. ложки
                            Яйцо - 1 шт.
                            Сахар - 2 ст. ложки
                            Сода - 0,5 ч. ложки
                            Лимонная кислота - 1/4 ч. ложки
                            Соль - 0,5 ч. ложки
                            Реально вкусно, сам готовил', 'http://gotocon.com/dl/photos/speakers/david_holmes.jpg');

insert into drivers
values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Doug', 'Lea', 100,
        'Могу в Лс ещё рецептов покидать:)', 'https://avatars.githubusercontent.com/u/2233919?v=4');

insert into drivers
values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Raoul-Gabriel', 'Urma', 100,
        'Машина так себе', 'https://pluralsight.imgix.net/author/lg/ff41683b-dece-4117-a5f9-b5b4d5a70175.png?w=200');

insert into drivers
values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Mario', 'Fusco', 100,
        'Лучший в службе', 'https://avatars.githubusercontent.com/u/372781?v=4');

insert into drivers
values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Alan', 'Mycroft', 100,
        'Боженька х2', 'https://www.cl.cam.ac.uk/~am21/am2014.jpg');

insert into drivers
values (14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Scott', 'Oaks', 100, '', 'https://dze0oudb6zz9z.cloudfront.net/insecure/fill/298/297/sm/0/plain/https://1851-prod.s3.amazonaws.com/author/090a4fc3750e50a91d905b3b77e33e629450.jpg');


insert into cars
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Hyinday Elantra', 'https://autoradar.top/wp-content/uploads/2020/03/new-hyundai-elantra-2021.jpg',
        'Красный', 2021, 1.6, 'AA1111AA');
insert into cars
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Toyota Camry', 'https://autonews.autoua.net/media/uploads/toyota/2021-toyota-camry-xse-hybrid.jpg',
        'Черный', 2021, 3.5, 'AA2222AA');
insert into cars
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Porsche Cayenne', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Porsche_Cayenne_S_%2892A%29_%E2%80%93_Frontansicht%2C_10._Oktober_2011%2C_W%C3%BClfrath.jpg/305px-Porsche_Cayenne_S_%2892A%29_%E2%80%93_Frontansicht%2C_10._Oktober_2011%2C_W%C3%BClfrath.jpg',
        'Красный', 2021, 2.5, 'AA3333AA');
insert into cars
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Toyota Camry', 'https://autonews.autoua.net/media/uploads/toyota/2021-toyota-camry-xse-hybrid.jpg',
        'Черный', 2021, 3.5, 'AA4444AA');

insert into driver_car
values (1, 1);
insert into driver_car
values (2, 2);
insert into driver_car
values (3, 3);
insert into driver_car
values (4, 4);
insert into driver_car
values (5, 2);
insert into driver_car
values (6, 3);