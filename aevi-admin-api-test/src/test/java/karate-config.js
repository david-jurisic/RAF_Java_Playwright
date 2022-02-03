function fn() {
    let env = karate.env;
    karate.log('karate.env system property was:', env);

    if (!env) {
        env = 'dev';
    }

    return {
        env: env,
        token: 'eyJhY2NvdW50SWQiOiJvbmJvYXJkaW5nLXVzZXIiLCJhcGlUb2tlbiI6IjB6dzF2akRlaE9kdXJOYzVVUUZmaEZSeFNpSHVPOXErYlQ3QlRiVVBlVEwwMmUxdzQxU0tUYlJEbXNaTnYrNngifQ==',
        baseUrl: 'https://localhost:8443/SMCAdmin/'
    }
}