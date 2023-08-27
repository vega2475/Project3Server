create table Sensors(
                        id int primary key generated always as identity,
                        name varchar not null UNIQUE
);

create table Measurements(
                             id int primary key generated always as identity,
                             value double precision not null,
                             raining boolean not null,
                             createdAt timestamp not null,
                             sensor varchar references Sensors(name)
);