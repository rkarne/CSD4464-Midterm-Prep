# CSD-4464 Midterm Prep Exercise

The following exercise is based on JSF, Collections and Database Connections.

The system that currently exists provides a JSF page that renders a list of 
contents from a database. To practice, your job is to add full CRUD operations
on this list.

Specifically, the following functionality should be achievable:

* Read a List of Guestbook Posts
* Add a Guestbook Post
* Edit a Guestbook Post
* Delete a Guestbook Post

It's possible to do this all on a single JSF page (ie- without navigation.) Your
solution may use one page, or several pages. It's up to you.

Note: This does NOT need login or any authentication. You're welcome to add it
in, but it's not necessary.

This is based on the following database structure. If you're on-site, you'll be
able to access the attached DB. If not, you can install it yourself on your home
system using XAMPP or otherwise.

```sql
CREATE TABLE guestbook (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, author VARCHAR(255), message TEXT);
INSERT INTO guestbook (author, message) VALUES ("Mysterious Stranger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis non urna ut quam rhoncus tempus. Nam viverra neque a viverra vulputate. Nam sit amet viverra ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc nec dapibus massa. Nam id neque placerat, viverra ligula vitae, suscipit augue. Ut semper, ex hendrerit venenatis convallis, mi mi rhoncus lorem, pellentesque tristique est magna id massa. Praesent in luctus mi, id elementum elit. Vivamus a augue nec lectus mattis rutrum. Quisque ultricies erat libero, eget commodo nisi sodales nec. Phasellus tempor gravida vulputate. Maecenas ligula tellus, aliquam sed turpis sit amet, rhoncus faucibus mi. Proin ut fringilla nisl. Pellentesque porta sed ligula eget tempor. Aenean viverra est sed elit congue, sed blandit risus egestas. Fusce libero diam, faucibus in scelerisque quis, bibendum non velit.");
```