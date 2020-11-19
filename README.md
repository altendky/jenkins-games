Setup
-----

- Download this gist and extract the contents.  Run the following `docker-compose` to bring up Jenkins.
  - `
docker-compose build --build-arg DOCKER_SOCK_GID=$(stat -c '%g' /var/run/docker.sock) && docker-compose up --renew-anon-volumes --remove-orphans
`

- Open http://localhost:8080/
- Enter password from Jenkins startup
- Select plugins to install
  - Select None
  - Select Install
- Skip and continue as admin
- Save and finish
- Start using Jenkins
- New Item
  - Set name to test
  - Select pipeline
  - Ok
  - Add the pipeline script from `Jenkinsfile.groovy`
  - Save
- Build now
