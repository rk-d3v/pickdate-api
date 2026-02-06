CREATE TABLE IF NOT EXISTS problem_events
(
    id             VARCHAR(255) PRIMARY KEY NOT NULL,
    status         INTEGER                  NOT NULL,
    detail         TEXT,
    instance       TEXT,
    title          TEXT,
    stack_trace    TEXT,
    invalid_params JSONB                    NULL,
    created_at     TIMESTAMP WITH TIME ZONE,
    updated_at     TIMESTAMP WITH TIME ZONE
);

CREATE INDEX IF NOT EXISTS idx_problem_events_status ON problem_events (status);
CREATE INDEX IF NOT EXISTS idx_problem_events_title ON problem_events (title);
CREATE INDEX IF NOT EXISTS idx_problem_events_created_at ON problem_events (created_at);
CREATE INDEX IF NOT EXISTS idx_problem_events_updated_at ON problem_events (updated_at);
