# Python TP Patterns

## Setup

```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

## Run tests

```bash
python -m pytest -q
```

## Run demos

```bash
python main.py
python main_tp2.py
python main_tp3.py
python main_patterns.py
```

## Patterns covered

- `Singleton` with `ScolariteManager`
- `Factory Method` with `PersonneFactory`
- `Decorator` with `EtudiantBoursierDecorator` and `EtudiantDelegueDecorator`
- `Adapter` with `LegacyCoursAdapter`
- `Strategy` with mention and sorting strategies
- `Observer` when a grade is added to an `Etudiant`
