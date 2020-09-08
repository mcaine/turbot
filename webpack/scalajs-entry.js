if (process.env.NODE_ENV === "production") {
    const opt = require("./turbot-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./turbot-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./turbot-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
