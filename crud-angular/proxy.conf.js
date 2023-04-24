const PROXY_CONFIG = [
  {
    context: ['/api'],
    target: 'http://localhost:8080',
    secure: false,
    logLeval: 'debug'
  }
];

module.exports = PROXY_CONFIG;
