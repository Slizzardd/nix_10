
insert into drivers
values (1, CURRENT_TIMESTAMP(), 'https://www.psi.ch/sites/default/files/styles/psi_content_half_sm/public/2020-02/bauer_christian_0005.jpg?itok=M83eoQ7p', CURRENT_TIMESTAMP(), true, 100, 'Christian', 'Bauer',
        'НЕАДЕВКАТ!!! Заказы лучше не давать');

insert into drivers
values (2, CURRENT_TIMESTAMP(), 'https://avatars.githubusercontent.com/u/579974?v=4', CURRENT_TIMESTAMP(), true, 120, 'Gavin', 'King',
        'Хороший человек, ухоженная машина');

insert into drivers
values (3, CURRENT_TIMESTAMP(), 'https://avatars.githubusercontent.com/u/1187639?v=4', CURRENT_TIMESTAMP(), true, 110, 'Gary', 'Gregory',
           'Просто нет слов');

insert into drivers
values (4, CURRENT_TIMESTAMP(), 'https://knigogid.ru/storage/authors/04e/276/1c8/e17719e1382e889b367db272c918413b.jpg', CURRENT_TIMESTAMP(), true, 150, 'Bruce', 'Eckel',
           'БОЖЕНЬКА');

insert into drivers
values (5, CURRENT_TIMESTAMP(), 'https://qconlondon.com/london-2017/sites/default/files/styles/img-single-track/public/10094934084_2006994711977a.jpg?itok=x4kf5DB5', CURRENT_TIMESTAMP(), true, 100, 'Brian', 'Goetz',
           'Просто хороший человек');

insert into drivers
values (6, CURRENT_TIMESTAMP(), 'https://avatars.githubusercontent.com/u/237258?v=4', CURRENT_TIMESTAMP(), true, 140, 'Tim', 'Peierls',
           'Я уже не знаю что писать');

insert into drivers
values (7, CURRENT_TIMESTAMP(), 'https://cdn.javarush.ru/images/article/2a6d3ec7-4b50-4201-b1ef-fa9b65642430/original.jpeg', CURRENT_TIMESTAMP(), true, 170, 'Joshua', 'Bloch',
           'Хороший человек, ухоженная машина');

insert into drivers
values (8, CURRENT_TIMESTAMP(), 'https://www.hbs.edu/news/PublishingImages/Joe%20Bower%20Headshot_small.jpg', CURRENT_TIMESTAMP(), true, 170, 'Joseph', 'Bowbeer',
           'ААААААААААААААААААААААААА');

insert into drivers
values (9, CURRENT_TIMESTAMP(), 'http://gotocon.com/dl/photos/speakers/david_holmes.jpg', CURRENT_TIMESTAMP(), true, 140, 'David', 'Holmes',
           'OOOOOOOOOOOOO');

insert into drivers
values (10, CURRENT_TIMESTAMP(), 'https://avatars.githubusercontent.com/u/2233919?v=4', CURRENT_TIMESTAMP(), true, 190, 'Doug', 'Lea',
           'Могу в Лс ещё рецептов покидать:)');

insert into drivers
values (11, CURRENT_TIMESTAMP(), 'https://pluralsight.imgix.net/author/lg/ff41683b-dece-4117-a5f9-b5b4d5a70175.png?w=200', CURRENT_TIMESTAMP(), true, 180, 'Raoul-Gabriel', 'Urma',
        'Машина так себе');

insert into drivers
values (12, CURRENT_TIMESTAMP(), 'https://avatars.githubusercontent.com/u/372781?v=4', CURRENT_TIMESTAMP(), true, 110, 'Mario', 'Fusco',
        'Лучший в службе');

insert into drivers
values (13, CURRENT_TIMESTAMP(), 'https://www.cl.cam.ac.uk/~am21/am2014.jpg', CURRENT_TIMESTAMP(), true, 100, 'Alan', 'Mycroft',
        'Боженька х2');

insert into drivers
values (14, CURRENT_TIMESTAMP(), 'https://dze0oudb6zz9z.cloudfront.net/insecure/fill/298/297/sm/0/plain/https://1851-prod.s3.amazonaws.com/author/090a4fc3750e50a91d905b3b77e33e629450.jpg', CURRENT_TIMESTAMP(), true, 100, 'Scott', 'Oaks',
        '');

insert into car
    value (1, CURRENT_TIMESTAMP(), 'https://autoradar.top/wp-content/uploads/2020/03/new-hyundai-elantra-2021.jpg',
           CURRENT_TIMESTAMP(), true, 'Hyinday Elantra', 'AA1111AA', 'Red', 1.6, 2021);
insert into car
    value (2, CURRENT_TIMESTAMP(), 'https://autonews.autoua.net/media/uploads/toyota/2021-toyota-camry-xse-hybrid.jpg',
           CURRENT_TIMESTAMP(), true, 'Toyota Camry', 'AA2222AA', 'Black', 2.5, 2011);
insert into car
    value (3, CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Porsche_Cayenne_S_%2892A%29_%E2%80%93_Frontansicht%2C_10._Oktober_2011%2C_W%C3%BClfrath.jpg/305px-Porsche_Cayenne_S_%2892A%29_%E2%80%93_Frontansicht%2C_10._Oktober_2011%2C_W%C3%BClfrath.jpg',
           CURRENT_TIMESTAMP(), true, 'Porsche Cayenne', 'AA3333AA', 'Black', 1.5, 2020);
insert into car
    value (4, CURRENT_TIMESTAMP(), 'https://s.auto.drom.ru/i24229/pubs/4/66507/2956995.jpg',
           CURRENT_TIMESTAMP(), true, 'Kia Ceed', 'KA9180ET', 'Black', 1.6, 2019);
insert into car
    value (5, CURRENT_TIMESTAMP(), 'https://cdn.euroncap.com/media/42972/volvo_v60_359_235.jpg?mode=crop&width=359&height=235',
        CURRENT_TIMESTAMP(), true, 'Volvo S60', 'AA6666AA', 'Black', 4.5, 1999);
insert into car
    value (6, CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/1/1f/2019_Porsche_Cayenne_V8_Turbo_Tiptronic_4.0_Front.jpg',
           CURRENT_TIMESTAMP(), true, 'Porsche Cayenne', 'AA5555AA', 'Black', 1.1, 2018);


insert into driver_car
    value (1, 1);
insert into driver_car
    value (1, 2);
insert into driver_car
    value (2, 1);
insert into driver_car
    value (2, 2);
insert into driver_car
    value (2, 3);
insert into driver_car
    value (3, 3);
insert into driver_car
    value (3, 4);
insert into driver_car
    value (4, 4);
insert into driver_car
    value (4, 5);
insert into driver_car
    value (5, 4);
insert into driver_car
    value (5, 5);
insert into driver_car
    value (6, 6);
insert into driver_car
    value (6, 5);
insert into driver_car
    value (7, 3);
insert into driver_car
    value (8, 2);
insert into driver_car
    value (9, 4);
insert into driver_car
    value (10, 2);
