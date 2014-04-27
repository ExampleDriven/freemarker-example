    and film_title in
         (select film_title
            from film_table
            where film_year equals ${film_year});